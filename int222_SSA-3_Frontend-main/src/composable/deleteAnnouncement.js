const URL = import.meta.env.VITE_API_ROOT

const deleteAnnouncementById = async (deleteId, announcements) => {
  if (confirm("Do you want to delete")) {
    try {
      const res = await fetch(`${URL}/${deleteId}`, { method: "DELETE" })
      if (res.ok) {
          announcements.value = announcements.value.filter((announcement) => {
          return announcement.id !== deleteId
        })
      } else {
        throw new Error("Error, can't delete announcement")
      }
    } catch (error) {
      console.log(error)
    }
  }
}

export { deleteAnnouncementById }
