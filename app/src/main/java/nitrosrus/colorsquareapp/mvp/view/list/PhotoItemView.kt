package nitrosrus.colorsquareapp.mvp.view.list

interface PhotoItemView {
    var pos: Int
    fun setTitle(title: String)
    fun loadImage(url: String)


}