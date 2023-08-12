<script setup>
import { ref, onMounted, watch, computed } from 'vue'
import { getAnnouncementByMode } from '../composable/getAnnouncementByMode'
import { useAnnouncementStore } from '../stores/announcementStore'
import { RouterLink } from 'vue-router'

const announcementStore = useAnnouncementStore()
const timezone = Intl.DateTimeFormat().resolvedOptions().timeZone
const mode = ref(announcementStore.mode)
const categoryNumber = ref(announcementStore.categoryNumber)

const announcements = ref([])
const currentPage = ref(1)
const itemsPerPage = 5
const allPages = ref(1)

const displayedPages = computed(() => {
  const pages = []
  const maxDisplayedPages = 10
  const totalAvailablePages = Math.min(allPages.value, maxDisplayedPages)

  let startPage = 1
  let endPage = totalAvailablePages

  if (allPages.value > maxDisplayedPages) {
    const offset = Math.min(Math.max(currentPage.value - maxDisplayedPages, 0), allPages.value - maxDisplayedPages)
    startPage = 1 + offset
    endPage = startPage + maxDisplayedPages - 1
  }

  for (let i = startPage; i <= endPage; i++) {
    pages.push(i)
  }
  return pages
})

onMounted(async () => {
    await fetchAnnouncements(mode.value, currentPage.value - 1, categoryNumber.value)
})

watch(() => announcementStore.mode, async (newMode) => {
    mode.value = newMode
    currentPage.value = 1
    await fetchAnnouncements(mode.value, currentPage.value - 1, categoryNumber.value)
})

watch(() => categoryNumber.value, async (newCategoryNumber) => {
    announcementStore.categoryNumber = newCategoryNumber
    currentPage.value = 1
    await fetchAnnouncements(mode.value, currentPage.value - 1, newCategoryNumber)
})

const fetchAnnouncements = async (mode, pageNumber, categoryNumber) => {
    try {
      const { content, totalPages } = await getAnnouncementByMode(mode, pageNumber, categoryNumber)
      announcements.value = content
      allPages.value = totalPages
    } catch (error) {
      console.log(error)
    }
    // console.log("Do mode = " + mode + ", pageNumber = " + pageNumber + ", categoryNumber = " + categoryNumber)
}

const goToPage = async (pageNumber) => {
    if (pageNumber < 1) pageNumber = 1
    if (pageNumber === currentPage.value) return
    if (pageNumber > allPages.value) pageNumber = allPages.value
    currentPage.value = pageNumber
    await fetchAnnouncements(mode.value, pageNumber - 1, categoryNumber.value)
}

const goToPreviousPage = async () => {
    goToPage(currentPage.value - 1)
}

const goToNextPage = async () => {
    goToPage(currentPage.value + 1)
}

const changeMode = () => {
    announcementStore.changeMode()
}
</script>
 
<template>
    <div>
        <div>
            <p class="text-center text-4xl pt-20"> SIT Announcement System (SAS)</p> 
        </div>
  
        <div class="mx-20 mt-10">
            <div class="flex">
                <p class="text-2xl">Date/Time shown in Timezone: {{ timezone }}</p>
            </div>

            <div class="flex mt-4">
                <select class="ann-category-filter pr-60 py-2 shadow rounded-lg" v-model="categoryNumber">
                    <option value="0">ทั้งหมด</option>
                    <option value="1">ทั่วไป</option>
                    <option value="2">ทุนการศึกษา</option>
                    <option value="3">หางาน</option>
                    <option value="4">ฝึกงาน</option>
                </select>
                <button class="ann-button bg-orange-300 p-2 ml-auto rounded-md hover:bg-orange-500" @click="changeMode">
                    {{ announcementStore.modeText }}
                </button>
            </div>
   
            <div class="mt-5 text-xl" id="table">
                <table v-if="announcementStore.mode === 'active'" class="table table-bordered table-striped">
                    <thead class="bg-gray-400 border">
                        <tr >
                            <th class="w-1/12 text-center">No.</th>
                            <th class="w-9/12">Title</th>
                            <th class="w-2/12 text-center">Category</th>
                        </tr>
                    </thead>

                    <tbody class="border">
                        <tr v-for="(announcement, index) in announcements" :key="index" class="ann-item border-t-2 border-b-2 hover:bg-orange-300">
                            <td class="text-center">{{ (currentPage - 1) * itemsPerPage + index + 1 }}</td>
                            <RouterLink :to="{ name: 'UserAnnouncementDetailActive', params: { id: announcement.id } }">
                                <td class="ann-title">{{ announcement.announcementTitle }}</td>
                            </RouterLink>
                            <td class="ann-category text-center">{{ announcement.announcementCategory }}</td>
                        </tr>
                    </tbody>
                </table>    

                <table v-if="announcementStore.mode === 'close'">
                    <thead class="bg-gray-400 border">
                        <tr>
                            <th class="w-1/12 text-center">No.</th>
                            <th class="w-7/12">Title</th>
                            <th class="w-2/12">Closed Date</th>
                            <th class="w-2/12 text-center">Category</th>
                        </tr>
                    </thead>

                    <tbody class="border">           
                        <tr v-for="(announcement, index) in announcements" :key="index" class="ann-item border-t-2 border-b-2 hover:bg-orange-300">
                            <td class="text-center">{{ (currentPage - 1) * itemsPerPage + index + 1 }}</td>
                            <RouterLink :to="{ name: 'UserAnnouncementDetailClose', params: { id: announcement.id } }">
                                <td class="ann-title">{{ announcement.announcementTitle }}</td>
                            </RouterLink>
                            <td class="ann-close-date">{{ new Date(announcement.closeDate).toLocaleString('en-UK', { day: 'numeric', month: 'short', year: 'numeric', hour: '2-digit', minute: '2-digit' })}}</td>
                            <td class="ann-category text-center">{{ announcement.announcementCategory }}</td>
                        </tr>
                    </tbody>
                </table>

                <div class="text-center mt-4" v-if="allPages > 1">
                    <button class="ann-page-prev bg-orange-300 p-2 mx-1 rounded-md hover:bg-orange-500"
                    @click="goToPreviousPage" :disabled="currentPage === 1">
                        Previous
                    </button>
                    <button v-for="(pageNumber, index) in displayedPages" :key="pageNumber" @click="goToPage(pageNumber)"
                    :class="[`ann-page-${index % 10} px-4 py-2 rounded-md mr-2`,
                    currentPage === pageNumber ? 'bg-sky-400 text-white' : 'bg-gray-200']">
                        {{ pageNumber }}
                    </button>
                    <button class="ann-page-next bg-orange-300 p-2 mx-1 rounded-md hover:bg-orange-500"
                    @click="goToNextPage" :disabled="currentPage === allPages">
                        Next
                    </button>
                </div>

                <p v-if="announcements.length === 0" class="text-4xl text-center text-red-500">No Announcement</p>
            </div>
        </div>
    </div>
</template>
  
<style>
table {
    width: 100%;
}

th, td {
    text-align: left;
    padding: 10px;
}
</style>
