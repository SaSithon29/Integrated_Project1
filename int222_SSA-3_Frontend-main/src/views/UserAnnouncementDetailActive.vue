<script setup>
import { getAnnouncementById } from '../composable/getAnnouncementById'
import { useRoute, RouterLink } from 'vue-router'
import { ref } from 'vue'



const announcementTitle = ref('')
const announcementCategory = ref('')
const announcementDescription = ref('')


const route = useRoute()
const id = route.params.id

getAnnouncementById(id).then((announcement) => {
  announcementTitle.value = announcement.announcementTitle
  announcementCategory.value = announcement.announcementCategory
  announcementDescription.value = announcement.announcementDescription
})
</script>
 
<template>
    <div class="mx-10">
      <div class="py-10 px-5 mt-20 border-4 border-orange-300 border-solid">
        <div class="ann-title text-3xl mb-5 font-medium">{{ announcementTitle }}</div>
        <div class="ann-category text-xl text-gray-400 font-medium mb-10">
            {{ announcementCategory }}
        </div>
        <div class="text-3xl font-medium mb-4">รายละเอียด:</div>
        <div class="ann-description text-3xl font-medium">{{ announcementDescription }}</div>
      </div>
      <div class="mt-7">
        <router-link :to="{ name: 'UserAnnouncements' }">
          <button class="ann-button bg-orange-300 p-2 rounded-md hover:bg-orange-500">
            Back
          </button>
        </router-link>
      </div>
    </div>
</template>
 
<style scoped>

</style>