const URL = import.meta.env.VITE_API_ROOT 
const getAnnouncementByMode = async (mode, pageNumber, categoryNumber) => {
  if(categoryNumber === 0 || categoryNumber === null || categoryNumber === undefined){
    try {
        const res = await fetch(`${URL}/pages?mode=${mode}&page=${pageNumber}&size=5`)
        if (res.ok) {
          const data = await res.json()
          const { content, totalPages } = data
          return { content, totalPages }
        } else {
          throw new Error("Error, can't get data")
        }
      } catch (error) {
        console.log(error)
      }
  }
  else {
    try {
      const res = await fetch(`${URL}/pages?mode=${mode}&category=${categoryNumber}&page=${pageNumber}&size=5`)
      if (res.ok) {
        const data = await res.json()
        const { content, totalPages } = data
        return { content, totalPages }
      } else {
        throw new Error("Error, can't get data")
      }
    } catch (error) {
      console.log(error)
    }
  }
}
export { getAnnouncementByMode }