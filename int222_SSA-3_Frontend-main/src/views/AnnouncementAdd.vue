<script setup>
const URL = import.meta.env.VITE_API_ROOT 


let title = ''
let categoryId = 0
let description = ''
let pDate = null
let pTime = null
let cDate = null
let cTime = null
let publishDate = null
let closeDate = null
let displayCheck = false

const checkDate = (pDate, pTime, cDate, cTime) => {

    if(pDate != null && pTime != null){
        publishDate = new Date(pDate + " " + pTime).toISOString()
    }
    if(cDate != null && cTime != null){
        closeDate = new Date(cDate + " " + cTime).toISOString()
    }
}

const createNewAnnouncement = async (title, categoryId, description, pDate, pTime, cDate, cTime, displayCheck) => {    
    checkDate(pDate, pTime, cDate, cTime)
    let display = (displayCheck == false) ? 'N' : 'Y'

    try {
        const res = await fetch(`${URL}`, { 
            method: "POST",
            headers: {
                'content-type': 'application/json'
            },
            body:JSON.stringify({
                announcementTitle: title,
                announcementDescription: description,
                publishDate: publishDate,
                closeDate: closeDate,
                announcementDisplay: display,
                categoryId: categoryId
            })
        })
        if(res.ok){
            alert("Add new announcement successful")
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
            <div class="text-4xl font-extrabold mt-6">Announcement Detail</div>
        </div>
        <div class="mt-5">
            <div class="text-3xl font-medium">Title</div>
            <input class="ann-title w-full text-2xl border-2 border-orange-300 rounded-lg" v-model="title"/>
        </div>
        <div class="mt-5">
            <div class="text-3xl font-medium">Category</div>
            <select class="ann-category mt-4 w-1/3 py-3 shadow rounded-lg" v-model="categoryId">
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
                <input class="ann-publish-date text-2xl border-2 rounded-lg border-orange-300 mr-3 " placeholder="01/05/2023" type="date" v-model="pDate"/>
                <input class="ann-publish-time text-2xl border-2 rounded-lg border-orange-300" placeholder="12:30" type="time" v-model="pTime"/>
            </div>
        </div>
        <div class="mt-5">
            <div class="text-3xl font-medium">Close Date</div>
            <div class="flex-cols">
                <input class="ann-close-date text-2xl border-2 rounded-lg border-orange-300 mr-3" placeholder="01/05/2023" type="date"  v-model="cDate"/>
                <input class="ann-close-time text-2xl border-2 rounded-lg border-orange-300" placeholder="12:30" type="time" v-model="cTime"/>
            </div>
        </div>
        <div class="mt-5">
            <div class="text-3xl font-medium">Display</div>
            <input type="checkbox" class="ann-display mr-3" v-model="displayCheck"/><span class="text-2xl">Check to show this announcement</span>
        </div>
        <div class="mt-5">
            <button class="ann-button bg-orange-300 p-2 rounded-md hover:bg-orange-500 mr-3" @click="createNewAnnouncement(title, categoryId, description, pDate, pTime, cDate, cTime, displayCheck)">Submit</button>
            <RouterLink :to="{ name: 'TableAnnouncement' }">
                <button class="ann-button bg-orange-300 p-2 rounded-md hover:bg-orange-500">Cancel</button>
            </RouterLink>
        </div>
    </div>
</template>
 
<style scoped>

</style>