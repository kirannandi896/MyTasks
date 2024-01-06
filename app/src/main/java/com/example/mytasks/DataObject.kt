package com.example.mytasks

object DataObject {
    var listdata = mutableListOf<CardInfo>()

    fun setData(title: String, priority: String, done: Boolean) {
        listdata.add(CardInfo(title, priority, done))
    }

    fun getAllData(): List<CardInfo> {
        return listdata
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


    fun updateData(pos:Int,title:String,priority:String,done:Boolean)
    {
        listdata[pos].title=title
        listdata[pos].priority=priority
        listdata[pos].done=done
    }

}