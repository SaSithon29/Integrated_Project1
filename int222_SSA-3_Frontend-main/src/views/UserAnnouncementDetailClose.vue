<script setup>
import { getAnnouncementById } from '../composable/getAnnouncementById'
import { useRoute, RouterLink } from 'vue-router'
import { ref, onMounted } from 'vue'
import { useAnnouncementStore } from '../stores/announcementStore'

const announcementStore = useAnnouncementStore()

const announcementTitle = ref('')
const announcementCategory = ref('')
const announcementDescription = ref('')
const announcementCloseDate = ref('')

const route = useRoute()
const id = route.params.id


getAnnouncementById(id).then((announcement) => {
  announcementTitle.value = announcement.announcementTitle
  announcementCategory.value = announcement.announcementCategory
  announcementDescription.value = announcement.announcementDescription
  announcementCloseDate.value = new Date(announcement.closeDate).toLocaleString('en-UK', { day: 'numeric', month: 'short', year: 'numeric', hour: '2-digit', minute: '2-digit' }) 
})



const goBack = () => {
  announcementStore.setModeToClosed()
}
</script>
 
<template>
    <div class="mx-10">
      <div class="py-10 px-5 mt-20 border-4 border-orange-300 border-solid">
        <div class="ann-title text-3xl mb-5 font-medium">{{ announcementTitle }}</div>
        <div class="ann-category text-xl text-gray-400 font-medium mb-2">{{ announcementCategory }}</div>
        <div class="ann-close-date text-red-500 text-xl font-medium mb-10">Close date: {{ announcementCloseDate }}</div>
        <div class="text-3xl font-medium mb-4">รายละเอียด:</div>
        <div class="ann-description text-3xl font-medium">{{ announcementDescription }}</div>
      </div>
      <div class="mt-7">
        <router-link :to="{ name: 'UserAnnouncements' }">
          <button class="ann-button bg-orange-300 p-2 rounded-md hover:bg-orange-500" @click="goBack">
            Back
          </button>
        </router-link>
      </div>
    </div>
</template>
 
<style scoped>

</style>