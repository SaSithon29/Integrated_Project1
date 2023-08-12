<script setup>
import { ref } from 'vue'
import { getAnnouncementById } from '../composable/getAnnouncementById'
import { useRoute, RouterLink } from 'vue-router'

const announcementId = ref('')
const announcementTitle = ref('')
const announcementCategory = ref('')
const announcementDescription = ref('')
const announcementPublishDate = ref('')
const announcementCloseDate = ref('')
const announcementDisplay = ref('')

const route = useRoute()
const id = route.params.id

getAnnouncementById(id).then((announcement) => {
  announcementId.value = announcement.id
  announcementTitle.value = announcement.announcementTitle
  announcementCategory.value = announcement.announcementCategory
  announcementDescription.value = announcement.announcementDescription
  announcementPublishDate.value = (announcement.publishDate == null) ? "-" : new Date(announcement.publishDate).toLocaleString('en-UK', { day: 'numeric', month: 'short', year: 'numeric', hour: '2-digit', minute: '2-digit' })
  announcementCloseDate.value = (announcement.closeDate == null) ? "-" : new Date(announcement.closeDate).toLocaleString('en-UK', { day: 'numeric', month: 'short', year: 'numeric', hour: '2-digit', minute: '2-digit' })
  announcementDisplay.value = announcement.announcementDisplay
})

</script>

<template>
  <div class="flex-row mt-20 mx-10 border-4 border-orange-300 border-solid">
    <div class="px-5">
      <div class="text-4xl font-extrabold mt-6">
        Announcement {{ announcementId }} Detail
      </div>

      <div class="mt-6">
        <span class="text-3xl font-medium">Titles:</span> <span class="ann-title font-medium text-3xl">{{ announcementTitle }}</span>
      </div>

      <div class="mt-6">
        <span class="text-3xl font-medium">Category:</span> <span class="ann-category font-medium text-3xl">{{ announcementCategory }}</span>
      </div>

      <div class="mt-6">
        <span class="text-3xl font-medium">Description:</span> <span class="ann-description font-medium text-3xl">{{ announcementDescription }}</span>
      </div>

      <div class="mt-6">
        <span class="text-3xl font-medium">Publish Date:</span> <span class="ann-publish-date font-medium text-3xl">{{ announcementPublishDate }}</span>
      </div>

      <div class="mt-6">
        <span class="text-3xl font-medium">Close Date:</span> <span class="ann-close-date font-medium text-3xl">{{ announcementCloseDate }}</span>
      </div>

      <div class="mt-6">
        <span class="text-3xl font-medium">Display:</span> <span class="ann-display font-medium text-3xl">{{ announcementDisplay }}</span>
      </div>

      <div class="mt-10 mb-10">
        <RouterLink :to="{ name: 'TableAnnouncement'}"><button class="ann-button bg-orange-300 p-3 px-6 rounded-md hover:bg-orange-500 mr-3">Back</button></RouterLink>
        <RouterLink :to="{ name: 'AnnouncementEdit', param: { id: announcementId } }">
          <button class="ann-button bg-orange-300 p-3 px-6 rounded-md hover:bg-orange-500">Edit</button>
        </RouterLink>
      </div>
    </div> 
  </div>
</template>

<style>

</style>


