import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useAnnouncementStore = defineStore('announcementStore', () => {
  const mode = ref('active') 
  const modeText = ref('Closed Announcements') 

  const categoryNumber = ref(0)
  const page = ref(1)


  function changeMode() {
    if (mode.value === 'active') {
      mode.value = 'close'
      modeText.value = 'Active Announcements' 
    } else {
      mode.value = 'active'
      modeText.value = 'Closed Announcements' 
    }
  }

  function setModeToClosed() {
    mode.value = 'close'
    modeText.value = 'Active Announcements'
  }

  // function setModeFromStore() {
  //   mode.value = announcementStore.mode
  //   if (mode.value === 'active') {
  //     modeText.value = 'Closed Announcements'
  //   } else {
  //     modeText.value = 'Active Announcements'
  //   }
  // }

  return { mode, modeText, categoryNumber, page, changeMode, setModeToClosed }
})
