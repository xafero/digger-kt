internal class _bag {
    var x = 0
    var y = 0
    var h = 0
    var v = 0
    var xr = 0
    var yr = 0
    var dir = 0
    var wt = 0
    var gt = 0
    var fallh = 0
    var wobbling = false
    var unfallen = false
    var exist = false

    fun copyFrom(t: _bag) {
        x = t.x
        y = t.y
        h = t.h
        v = t.v
        xr = t.xr
        yr = t.yr
        dir = t.dir
        wt = t.wt
        gt = t.gt
        fallh = t.fallh
        wobbling = t.wobbling
        unfallen = t.unfallen
        exist = t.exist
    }
}