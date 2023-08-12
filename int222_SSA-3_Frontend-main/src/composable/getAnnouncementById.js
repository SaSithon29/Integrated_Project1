const URL = import.meta.env.VITE_API_ROOT 
const getAnnouncementById = async (id) => {
  try{
    const res = await fetch(`${URL}/${id}`)
    if(res.ok){
      const announcements = res.json()
      return announcements
    }
    else {
      throw new error("Error, can't get data")
    }
  }
  catch (error){
    console.log(error)
  }
  alert("The request page is not available!")
}
export { getAnnouncementById }