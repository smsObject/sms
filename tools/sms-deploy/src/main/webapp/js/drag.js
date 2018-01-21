/**
 * WMS系统自定义拖拽工具类
 * @param id
 * @constructor
 */
function Drag(id) {
    var _this = this;
    this.oDiv = document.getElementById(id);//当前对象的id
    this.disX = 0;//初始坐标0
    this.disY = 0;

    //执行鼠标按下的动作onmousedown
    this.oDiv.onmousedown = function (ev) {
        _this.fnDown(ev);
        return false;
    }
}
Drag.prototype.fnDown = function (ev) {
    var _this = this;
    var oEvent = ev || event;

    //鼠标指针当前的水平坐标值-对象初始的地方的水平坐标值=鼠标水平方向的移动距离
    this.disX = oEvent.clientX - this.oDiv.offsetLeft;

    this.disY = oEvent.clientY - this.oDiv.offsetTop;

    document.onmousemove = function (ev) {
        _this.fnMove(ev);
    };
    document.onmouseup = function () {
        _this.fnUp();
    };
}
Drag.prototype.fnMove = function (ev) {
    var oEvent = ev || event;

    //鼠标指针当前的水平坐标值-鼠标水平方向移动的距离=对象初始地方那个的水平坐标
    var x = oEvent.clientX - this.disX;
    var y = oEvent.clientY - this.disY;

    this.oDiv.style.left = x + "px";//这个px必须加。不然没效果
    this.oDiv.style.top = y + "px";
}
Drag.prototype.fnUp = function () {
    //停止移动的时候,都清空
    document.onmousemove = null;
    document.onmouseup = null;
}


//继承过来的 实现拖动在框口区域内
function limitDrag(id) {
    //函数伪装
    Drag.call(this, id);
}
//原型继承 避免引用的问题会导致后面的修改影响父级的属性
for (var i in Drag.prototype) {
    limitDrag.prototype[i] = Drag.prototype[i];
}

limitDrag.prototype.fnMove = function (ev) {
    var oEvent = ev || event;
    var x = oEvent.clientX - this.disX;
    var y = oEvent.clientY - this.disY;
    if (x < 0) {
        x = 0;
    }
    else if (x > document.documentElement.clientWidth - this.oDiv.offsetWidth) {
        x = document.documentElement.clientWidth - this.oDiv.offsetWidth;
    }
    if (y < 0) {
        y = 0;
    }
    else if (y > document.documentElement.clientHeight - this.oDiv.offsetHeight) {
        y = document.documentElement.clientHeight - this.oDiv.offsetHeight;
    }
    this.oDiv.style.left = x + "px";
    this.oDiv.style.top = y + "px";
}