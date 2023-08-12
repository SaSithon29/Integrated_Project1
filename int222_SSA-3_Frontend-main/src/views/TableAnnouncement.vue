<script setup>
import { ref, onMounted } from 'vue'
import { getAnnouncements } from '../composable/getAnnouncements'
import { deleteAnnouncementById } from '../composable/deleteAnnouncement'
import { RouterLink } from 'vue-router'

const announcements = ref([])

onMounted(async () => {
  announcements.value = await getAnnouncements()
})

const handleDeleteAnnouncement = (deleteId) => {
  deleteAnnouncementById(deleteId, announcements)
}

const timezone = Intl.DateTimeFormat().resolvedOptions().timeZone
</script>
<template>
  <div>
    <div>
        <p class="text-center text-4xl pt-20"> SIT Announcement System (SAS)</p> 
    </div>

    <div class="mx-20 mt-10">
        <div>
            <p class="text-2xl">Date/Time shown in Timezone: {{ timezone }}</p>
        </div>

        <div class="mt-5 text-xl" id="table">
          <table v-if="announcements.length > 0" class="table table-bordered table-striped">
            <thead class="bg-gray-400">
                <tr>
                    <th>No.</th>
                    <th>Title</th>
                    <th>Category</th>
                    <th>Publish Date</th>
                    <th>Close Date</th>
                    <th class="text-center">Display</th>
                    <th class="text-center">Action</th>
                </tr>
            </thead>

            <tbody class=" border-l-2 border-r-2">
                <tr v-for="(announcement, index) in announcements" :key="index" class="ann-item border-t-2 border-b-2 ">
                    <td>{{ index + 1 }}</td>
                    <td class="ann-title">{{ announcement.announcementTitle }}</td>
                    <td class="ann-category">{{ announcement.announcementCategory }}</td>

                    
                    <td v-if="announcement.publishDate === null" class="ann-publish-date">-</td>
                    <td v-else class="ann-publish-date">{{ new Date(announcement.publishDate).toLocaleString('en-UK', { day: 'numeric', month: 'short', year: 'numeric', hour: '2-digit', minute: '2-digit' }) }}</td>

                    <td v-if="announcement.closeDate === null" class="ann-close-date">-</td>
                    <td v-else class="ann-close-date">{{ new Date(announcement.closeDate).toLocaleString('en-UK', { day: 'numeric', month: 'short', year: 'numeric', hour: '2-digit', minute: '2-digit' }) }}</td>


                    <td class="ann-display text-center">{{ announcement.announcementDisplay }}</td>
                    <td class="flex justify-center">
                      <RouterLink :to="{ name: 'AnnouncementDetail', 
                        params: { id: announcement.id } }" 
                        class="ann-button bg-orange-300 p-2 rounded-md hover:bg-orange-500 m-1">
                        View
                      </RouterLink>
                      <button class="ann-button bg-red-300 p-2 rounded-md hover:bg-red-500 m-1" @click="handleDeleteAnnouncement(announcement.id)">Delete</button>
                    </td>  
                </tr>
            </tbody>
          </table> 
          <p v-else class="text-4xl text-center text-red-500">No Announcement</p>
        </div>
        <div class="mt-8">
          <RouterLink :to="{ name: 'AnnouncementAdd' }" class="ann-button bg-orange-300 p-4 rounded-md hover:bg-orange-500">
            Add announcement
          </RouterLink>
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
