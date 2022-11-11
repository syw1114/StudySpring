var cnj_bg=new Array()
var cnj_bgg=new Array()
cnj_bgg[0]="/static/images/메인이미지3.jpeg"
cnj_bg[0]="/static/images/메인이미지1.jpeg"
cnj_bg[1]="/static/images/메인이미지2.jpeg"
cnj_bg[2]="/static/images/main5.jpg"
cnj_bg[3]="/static/images/main4.jpg"

var speed=3000  // 3초
var processed=new Array()
document.body.background = cnj_bgg[0]

    for (i=0;i<cnj_bg.length;i++){
        processed[i]= new Image()
        processed[i].src = cnj_bg[i]
    }
    var inc=-1
    function slideback(){
        if (inc <cnj_bg.length-1)
            inc++
        else
            inc=0
    document.body.background = processed[inc].src
    }
        if (document.all||document.getElementById)
        window.onload=new Function('setInterval("slideback()",speed)')