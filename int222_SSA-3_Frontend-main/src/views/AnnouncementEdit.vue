<script setup>
const URL = import.meta.env.VITE_API_ROOT 
import { useRoute, RouterLink } from 'vue-router'
import { getAnnouncementById } from '../composable/getAnnouncementById'
import { ref, onMounted, computed } from 'vue'


const title = ref('')
const category = ref('')
const categoryId = ref(1)
const description = ref('')
const publishDate = ref(null)
const closeDate = ref(null)
const announcement = ref()
const displayCheck = ref(false)
const pDate = ref()
const pTime = ref()
const cDate = ref()
const cTime = ref()

////// old data ///////
const oldTitle = ref()
const oldCategoryId = ref()
const oldCategory = ref()
const oldDescription = ref()
const oldDisplay = ref()

const oldPDate = ref()
const oldPTime = ref()

const oldCDate = ref()
const oldCTime = ref()
////// old data ///////

const route = useRoute()
const id = route.params.id

let format = { day: '2-digit', month: '2-digit', year: 'numeric', hour: '2-digit', minute: '2-digit' }

const splitPublishDate = () => {
    if(publishDate.value != null) {
        let pdDate = publishDate.value.toLocaleString('en-UK', format).slice(0,10)
        pTime.value = publishDate.value.toLocaleString('en-UK', format).slice(12,17)
        oldPTime.value = publishDate.value.toLocaleString('en-UK', format).slice(12,17)

        let publishDateParts = pdDate.split('/')
        let publishDay = publishDateParts[0]
        let publishMonth = publishDateParts[1]
        let publishYear = publishDateParts[2]
        pDate.value = `${publishYear}-${publishMonth}-${publishDay}` 
        oldPDate.value = `${publishYear}-${publishMonth}-${publishDay}`
    }
}

const splitCloseDate = () => {
    if(closeDate.value != null) {
        let cdDate = closeDate.value.toLocaleString('en-UK', format).slice(0,10)
        cTime.value = closeDate.value.toLocaleString('en-UK', format).slice(12,17)
        oldCTime.value = closeDate.value.toLocaleString('en-UK', format).slice(12,17)

        let closeDateParts = cdDate.split('/')
        let closeDay = closeDateParts[0]
        let closeMonth = closeDateParts[1]
        let closeYear = closeDateParts[2]
        cDate.value = `${closeYear}-${closeMonth}-${closeDay}`
        oldCDate.value = `${closeYear}-${closeMonth}-${closeDay}`
    }
}



const isFormUnchanged = computed(() => {
  if (
    title.value === oldTitle.value &&
    description.value === oldDescription.value &&
    pDate.value === oldPDate.value &&
    pTime.value === oldPTime.value &&
    cDate.value === oldCDate.value &&
    cTime.value === oldCTime.value &&
    displayCheck.value === oldDisplay.value &&
    categoryId.value === oldCategoryId.value
  ) {
    return true
  }
  else {
    return false
  }
})



onMounted(async () => {
    announcement.value = await getAnnouncementById(id)
    title.value = announcement.value.announcementTitle
    category.value = announcement.value.announcementCategory
    checkCategoryId(category.value)
    description.value = announcement.value.announcementDescription
    displayCheck.value = (announcement.value.announcementDisplay === 'Y') ? true : false

    publishDate.value = (announcement.value.publishDate === null) ? null : new Date(announcement.value.publishDate)
    closeDate.value = (announcement.value.closeDate === null) ? null : new Date(announcement.value.closeDate)
    splitPublishDate()
    splitCloseDate()

    oldTitle.value = announcement.value.announcementTitle
    oldCategory.value = announcement.value.announcementCategory
    checkOldCategoryId(oldCategory.value)
    oldDescription.value = announcement.value.announcementDescription
    oldDisplay.value = (announcement.value.announcementDisplay === 'Y') ? true : false
})

const checkCategoryId = (category) => {
    if (category === 'ทั่วไป') {
        categoryId.value = 1
    }
    if (category === 'ทุนการศึกษา') {
        categoryId.value = 2
    }
    if (category === 'หางาน') {
        categoryId.value = 3
    }
    if (category === 'ฝึกงาน') {
        categoryId.value = 4
    }
} 

const checkOldCategoryId = (category) => {
    if (category === 'ทั่วไป') {
        oldCategoryId.value = 1
    }
    if (category === 'ทุนการศึกษา') {
        oldCategoryId.value = 2
    }
    if (category === 'หางาน') {
        oldCategoryId.value = 3
    }
    if (category === 'ฝึกงาน') {
        oldCategoryId.value = 4
    }
} 

const createPublishDate = (pDate, pTime) => {
    let newPublishDate 

    if (pTime === null || typeof pTime === 'undefined') {
        newPublishDate = null
    }

    else if (pTime.length === 0) {
        newPublishDate = null
    }
    
    else if (pDate != null) {
        let pd = new Date(pDate + " " + pTime).toLocaleString()
        newPublishDate = (pd === 'Invalid Date') ? null : new Date(pd).toISOString()
    }
    else {
        newPublishDate = null
    }
    return newPublishDate
}

const createCloseDate = (cDate, cTime) => {
    let newCloseDate 

    if (cTime === null || typeof cTime === 'undefined') {
        newCloseDate = null
    }

    else if (cTime.length === 0) {
        newCloseDate = null
    }
    
    else if (cDate != null) {
        let cd = new Date(cDate + " " + cTime).toLocaleString()
        newCloseDate = (cd === 'Invalid Date') ? null : new Date(cd).toISOString()
    }
    else {
        newCloseDate = null
    }
    return newCloseDate
}

const editAnnouncement = async (title, description, pDate, pTime, cDate, cTime, displayCheck, categoryId) => {

    let newPublishDate = createPublishDate(pDate, pTime)
    let newCloseDate = createCloseDate(cDate, cTime)
    let display = (displayCheck == false) ? 'N' : 'Y'

    try {
        const res = await fetch(`${URL}/${id}`, { 
            method: "PUT",
            headers: {
                'content-type': 'application/json'
            },
            body:JSON.stringify({
                announcementTitle: title,
                announcementDescription: description,
                publishDate: newPublishDate,
                closeDate: newCloseDate,
                announcementDisplay: display,
                categoryId: categoryId    
            })
        })
        if(res.ok){
            alert("Edit announcement successful")
        }
    }
    catch (err) {
        console.log(err)
    }
}
</script>
 
<template>
    <div class="mx-10">
        <div>
            <div class="text-4xl font-extrabold mt-6">Announcement {{ id }} Edit</div>
        </div>
        <div class="mt-5">
            <div class="text-3xl font-medium">Title</div>
            <input class="ann-title w-full text-2xl border-2 border-orange-300 rounded-lg" v-model="title" />
        </div>
        <div class="mt-5">
            <div class="text-3xl font-medium">Category</div>
            <select class="ann-category mt-4 w-1/3 py-3 shadow rounded-lg" v-model="categoryId" >
                <option value="1">ทั่วไป</option>
                <option value="2">ทุนการศึกษา</option>
                <option value="3">หางาน</option>
                <option value="4">ฝึกงาน</option>
            </select>
        </div>
        <div class="mt-5">
            <div class="text-3xl font-medium">Description</div>
            <textarea class="ann-description w-full text-2xl border-2 border-orange-300 rounded-lg" rows="5" v-model="description"></textarea>
        </div>
        <div class="mt-5">
            <div class="text-3xl font-medium">Publish Date</div>
            <div class="flex-cols">
                <!-- <input class="ann-publish-date text-2xl border-2 rounded-lg border-orange-300 w-1/2" v-model="publishDate"/> -->
                <input class="ann-publish-date text-2xl border-2 rounded-lg border-orange-300 mr-3 " placeholder="01/05/2023" type="date" v-model="pDate"/>
                <input class="ann-publish-time text-2xl border-2 rounded-lg border-orange-300" placeholder="12:30" type="time" v-model="pTime"/>
            </div>
        </div>
        <div class="mt-5">
            <div class="text-3xl font-medium">Close Date</div>
            <div class="flex-cols">
                <!-- <input class="ann-close-date text-2xl border-2 rounded-lg border-orange-300 w-1/2" v-model="closeDate"/> -->
                <input class="ann-close-date text-2xl border-2 rounded-lg border-orange-300 mr-3" placeholder="01/05/2023" type="date"  v-model="cDate"/>
                <input class="ann-close-time text-2xl border-2 rounded-lg border-orange-300" placeholder="12:30" type="time" v-model="cTime"/>
            </div>
        </div>
        <div class="mt-5">
            <div class="text-3xl font-medium">Display</div>
            <!-- <input class="ann-display text-2xl border-2 rounded-lg border-orange-300" v-model="display"/> -->
            <input type="checkbox" class="ann-display mr-3" v-model="displayCheck"/><span class="text-2xl">Check to show this announcement</span>
        </div>
        <div class="mt-5">
            <button class="ann-button bg-orange-300 p-2 rounded-md hover:bg-orange-500 mr-3" 
            @click="editAnnouncement(title, description, pDate, pTime, cDate, cTime, displayCheck, categoryId)"
            :disabled="isFormUnchanged">
                Edit
            </button>
            <RouterLink :to="{ name: 'TableAnnouncement' }">
                <button class="ann-button bg-orange-300 p-2 rounded-md hover:bg-orange-500">Cancel</button>
            </RouterLink>
        </div>
    </div>
</template>
 
<style scoped>

</style>