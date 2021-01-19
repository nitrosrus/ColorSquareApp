package nitrosrus.colorsquareapp.mvp.view.list

interface UserItemView {
    var pos: Int
    fun setName(name: String)
    fun setClickListener()
}