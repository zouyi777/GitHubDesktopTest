let ul = document.getElementById('ul');  // 获取ul列表
let div = document.getElementById('article') // 获取包裹ul列表的div(css:  overflow:scroll;)
let text = document.getElementById('pull_down');
let start;  // 辅助变量：触摸开始时，相对于文档顶部的Y坐标
let num = 11; // 添加li文本，可自定义
let refresh = false;
let isLoad = false;
let pull_up=document.getElementById('pull_up');
let loading=document.getElementById('loading');
let refreshWrap=document.getElementById('refresh_wrap');
let refreshing=document.getElementById('refreshing');
let pullArrow = document.getElementById('pull_arrow');

/**
 * 上拉加载
 */
function addLi() {  // 添加li的方法，可自定义
    let fragment = document.createDocumentFragment();
    for(let i=0;i<10;i++) {
        let li = document.createElement('li');
        li.className = 'li';
        li.innerHTML = num++;
        fragment.appendChild(li); // 用DocumentFragment提高渲染速度
    }
    ul.appendChild(fragment);
}
div.addEventListener('scroll',function(){
    //if(div.scrollHeight-div.scrollTop<1000 && isLoad===false) {
    if(div.scrollTop+div.clientHeight>=div.scrollHeight  && isLoad===false){
        loading.style.display="";
        pull_up.innerHTML="加载中";
        isLoad = true;
        setTimeout(function(){
            isLoad = false;
            addLi();
            loading.style.display="none";
            pull_up.innerHTML="上拉加载更多";
        },3000)  //  节流阀
    }
},false);

/**
 * 下拉刷新
 */
div.addEventListener('touchstart',function(event){
    let touch = event.touches[0];
    start = touch.pageY;  // 辅助变量：触摸开始时，相对于文档顶部的Y坐标
},false);
div.addEventListener('touchmove',function(event){
    // 下拉刷新
    let touch = event.touches[0];
    if(div.scrollTop<=0){
        // 如果ul列表到顶部，修改ul列表的偏移,显示“下拉刷新”，并准备触发下拉刷新功能，可自定义
        refreshWrap.style.marginTop = parseFloat(refreshWrap.style.marginTop) + touch.pageY - start +'px';
        start = touch.pageY;
        // 若ul偏移量过大,则修改文字,refresh置为true,配合'touchend'刷新
        if(parseFloat(refreshWrap.style.marginTop) >= 0) {
            text.innerHTML = "释放刷新";
            pullArrow.style.transform = "rotate(180deg)";//箭头方向旋转向上
            refresh = true;
        }
    }
},false);

div.addEventListener('touchend',function(event){
    // 若'touchend'时，ul偏移,用setInterval循环恢复ul的偏移量
    if(div.offsetTop>=0) {
        refreshing.style.display = "";
        text.innerHTML = "正在刷新";
        pullArrow.style.display = "none";
        setTimeout(function(){
           var time= setInterval(function(){
                refreshWrap.style.marginTop = parseFloat(refreshWrap.style.marginTop) -3 +'px';
                // 若ul的偏移量恢复，clearInterval
                if(parseFloat(refreshWrap.style.marginTop)<= -59){
                    clearInterval(time);
                    refreshWrap.style.marginTop = "-60px";
                    text.innerHTML = "下拉刷新";
                    refreshing.style.display = "none";
                    pullArrow.style.transform = "none";//恢复旋转
                    pullArrow.style.display = "";
                }
            })
        },3000)  //  节流阀
    }
},false);