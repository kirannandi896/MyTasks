package com.example.mytasks

object DataObject {
    var listdata = mutableListOf<CardInfo>()

    fun setData(title: String, priority: String, ftime: String, ttime: String, fdate: String, tdate: String, done: Boolean) {
        listdata.add(CardInfo(title, priority, ftime, ttime, fdate, tdate, done))
    }

    fun getAllData(): List<CardInfo> {
        return listdata
    }

    fun filterlist(priority: String): List<CardInfo> {
        return listdata.filter { it.priority == priority }
    }
    
    fun filterlistdone(done: Boolean): List<CardInfo> {
        return listdata.filter { it.done == done}
    }

    fun searchlist(title: String): List<CardInfo> {
        return listdata.filter { it.title == title }
    }

    fun deleteAll(){
        listdata.clear()
    }

    fun getData(pos:Int): CardInfo {
        return listdata[pos]
    }

    fun deleteData(pos:Int){
        listdata.removeAt(pos)
    }



    fun updateData(pos:Int,title:String,priority:String, ftime: String, ttime: String, fdate: String, tdate: String, done:Boolean)
    {
        listdata[pos].title=title
        listdata[pos].priority=priority
        listdata[pos].ftime=ftime
        listdata[pos].ttime=ttime
        listdata[pos].fdate=fdate
        listdata[pos].tdate=tdate
        listdata[pos].done=done
    }

}