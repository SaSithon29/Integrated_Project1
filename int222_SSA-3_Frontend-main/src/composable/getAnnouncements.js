const URL = import.meta.env.VITE_API_ROOT 
const getAnnouncements = async () => {
  try{
    const res = await fetch(`${URL}`)
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
}
export { getAnnouncements }