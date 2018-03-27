<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="../../ext/ext-all.js" type="text/javascript"></script>
    <link href="../../ext/resources/css/ext-all.css" rel="stylesheet" type="text/css" />
     <link href="../../Resources/Css/OverExt_Base.css" rel="stylesheet" type="text/css" />
    <script src="../../ext/locale/ext-lang-zh_CN.js" type="text/javascript"></script>
    <script src="../../js/jquery/jquery-1.10.1.min.js" type="text/javascript"></script>
      <script src="../../JavaScript/MsgTip.js" type="text/javascript"></script>
       <script type="text/javascript" src="http://api.map.baidu.com/api?v=1.5&ak=sNyur13vORywDFGWIkwSmuDi"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
    <link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
 <style type="text/css">
        body, html
        {
            width: 100%;
            height: 100%;
            overflow: hidden;
            margin: 0;
        }
        #allmap
        {
            margin-right: 300px;
            height: 100%;
            overflow: hidden;
        }
        #result
        {
            border-left: 1px dotted #999;
            height: 100%;
            width: 295px;
            position: absolute;
            top: 0px;
            right: 0px;
            font-size: 12px;
        }
        dl, dt, dd, ul, li
        {
            margin: 0;
            padding: 0;
            list-style: none;
        }
        p
        {
            font-size: 12px;
        }
        dt
        {
            font-size: 14px;
            font-family: "微软雅黑";
            font-weight: bold;
            border-bottom: 1px dotted #000;
            padding: 5px 0 5px 5px;
            margin: 5px 0;
        }
        dd
        {
            padding: 5px 0 0 5px;
        }
        li
        {
            padding-left: 5px;
        }
        botton
        {
            height: 25px;
            width: 50px;
        }
    </style>
    <style type="text/css">
        .anchorBL
        {
            display: none;
        }
    </style>
    <title>区域展示</title>
</head>
<body>
    <div id="allmap" style="overflow: hidden; zoom: 1; position: relative;">
        <div id="map" style="height: 100%; -webkit-transition: all 0.5s ease-in-out; transition: all 0.5s ease-in-out;">
        </div>
        <div id="panelWrap" style="width: 0px; position: absolute; top: 0px; right: 0px;
            height: 100%; overflow: auto; -webkit-transition: all 0.5s ease-in-out; transition: all 0.5s ease-in-out;">
            <div style="width: 20px; height: 200px; margin: -100px 0 0 -10px; color: #999; position: absolute;
                opacity: 0.5; top: 50%; left: 50%;" id="showOverlayInfo">
                此处用于展示覆盖物信息
            </div>
            <div id="panel" style="position: absolute;">
            </div>
        </div>
    </div>
    <div id="result">
        <dl>
            <dt>区域绘制功能</dt>
            <dd>
                <ul>
                    <li>
                        <label>
                            <input type="radio" name="openClose1" onclick="addOverlay();" />新增</label>
                        <label>
                            <input type="radio" name="openClose1" onclick="editOverlay();" />修改</label>
                        <label>
                            <input id="closeOverlay" type="radio" name="openClose1" onclick="closeOverlay();"
                                checked="checked" />关闭</label>
                        <button onclick="delOverlay();">
                            清除</button>
                    </li>
                </ul>
            </dd>
            <dt style="line-height: 25px;">文字绘制功能</dt>
            <dd>
                <ul>
                    <li>
                        <label style="margin-left: 5px;">
                            显示文字：</label><input type="text" id="marker_title" /><br />
                        <label>
                            <input type="radio" name="openClose2" onclick="addMarker();" />新增</label>
                        <label>
                            <input id="closeMarker" type="radio" name="openClose2" onclick="closeMarker();" checked="checked" />关闭</label>
                        <button onclick="delMarker();">
                            清除</button>
                    </li>
                </ul>
            </dd>
            <dt>扩展功能</dt>
            <dd>
                <ul>
                    <li style="line-height: 21px;">
                        <label style="margin-left: 5px;">
                            地址：</label><input type="text" id="defaultaddress" />
                        <button onclick="getfedault();">
                            查询</button><br />
                        <input type="checkbox" name="fruit" style="vertical-align: middle;" onclick="ShowOther('uparea');"
                            id="uparea" />
                        上级区域&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="checkbox" name="fruit" style="vertical-align: middle;" onclick="ShowOther('upname');"
                            id="upname" />
                        上级名称<br />
                        <input type="checkbox" name="fruit" style="vertical-align: middle;" onclick="ShowOther('thisarea');"
                            id="thisarea" />
                        本级区域&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="checkbox" name="fruit" style="vertical-align: middle;" onclick="ShowOther('thisname');"
                            id="thisname" />
                        本级名称<br />
                        <input type="checkbox" name="fruit" style="vertical-align: middle;" onclick="ShowOther('downarea');"
                            id="downarea" />
                        下级区域&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="checkbox" name="fruit" style="vertical-align: middle;" onclick="ShowOther('downname');"
                            id="downname" />
                        下级名称 </li>
                </ul>
            </dd>
            <dt>数据操作</dt>
            <dd>
                <ul>
                    <li>
                        <button onclick="SaveData()">
                            保存</button>
                    </li>
                </ul>
            </dd>
        </dl>
    </div>
    <script type="text/javascript">
        function keyUp(e) {
            var currKey = 0, e = e || event;
            currKey = e.keyCode || e.which || e.charCode;
            var keyName = String.fromCharCode(currKey);

            if (currKey == 46) {
                if (tmpobj == null) {
                    alert("请选择要移除的对象");
                    return;
                }
                map.removeOverlay(tmpobj);

                for (var i = 0; i < overlays.length; i++) {
                    if (overlays[i] == tmpobj) {
                        overlays.splice(i, 1);
                        break;
                    }
                }
                for (var i = 0; i < markers.length; i++) {
                    if (markers[i] == tmpobj) {
                        markers.splice(i, 1);
                        break;
                    }
                }
            }
        }
        document.onkeyup = keyUp;


        function GetQueryString(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]); return null;
        }
        var map;
        var MapViewType = GetQueryString("MapViewType");

        var map = new BMap.Map("map", { mapType: BMAP_HYBRID_MAP });
        map.addControl(new BMap.MapTypeControl({
            mapTypes: [BMAP_NORMAL_MAP, BMAP_HYBRID_MAP]
        }));
        //		if (MapViewType == "2") {
        //			map = new BMap.Map('map', { mapType: BMAP_HYBRID_MAP });
        //		}
        //		else {
        //			map = new BMap.Map('map');
        //		}
        var mcid = GetQueryString("mcid");
        var mc_name = GetQueryString("mcname");
        var mclevel = GetQueryString("mclevel");

        var sMapPoint = GetQueryString("point"); //== "" ? window.top.document.getElementById("MapPoint").value : GetQueryString("point");
        if (sMapPoint == "") { sMapPoint = "120.964355,31.916724"; }

        var poi = new BMap.Point(sMapPoint.split(",")[0], sMapPoint.split(",")[1]);
        map.centerAndZoom(poi, 14);
        map.enableScrollWheelZoom();
        map.addControl(new BMap.MapTypeControl({
            mapTypes: [BMAP_NORMAL_MAP, BMAP_HYBRID_MAP]
        }));
        document.getElementById("marker_title").value = mc_name;
        document.getElementById("defaultaddress").value = mc_name;
        var overlays = [];
        var markers = [];
        var tmpobj;
        var styleOptions = {
            strokeColor: "red",    //边线颜色。
            fillColor: "blue",      //填充颜色。当参数为空时，圆形将没有填充效果。
            strokeWeight: 2,       //边线的宽度，以像素为单位。
            strokeOpacity: 0.8,    //边线透明度，取值范围0 - 1。
            fillOpacity: 0.2,      //填充的透明度，取值范围0 - 1。
            strokeStyle: 'solid' //边线的样式，solid或dashed。
        }
        //实例化鼠标绘制工具
        var drawingManager = new BMapLib.DrawingManager(map, {
            isOpen: false, //是否开启绘制模式
            enableDrawingTool: false, //是否显示工具栏
            drawingToolOptions: {
                anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
                offset: new BMap.Size(5, 5), //偏离值
                scale: 0.8 //工具栏缩放比例
            },
            polygonOptions: styleOptions //多边形的样式
        });

        //回调获得区域覆盖物信息
        var overlaycomplete = function (e) {
            overlays.push(e.overlay);
            e.overlay.addEventListener("click", function () { tmpobj = this; });
        };
        //添加鼠标绘制工具监听事件，用于获取绘制结果
        drawingManager.addEventListener('overlaycomplete', overlaycomplete);
        function $(id) {
            return document.getElementById(id);
        }

        //新增区域覆盖物
        function addOverlay() {
            tmpobj = null;
            closeMarker();
            document.getElementById("closeMarker").checked = true;
            if (overlays.length) {
                for (var i = 0; i < overlays.length; i++) {
                    overlays[i].disableEditing();
                }
            }
            drawingManager.open();
            drawingManager.setDrawingMode(BMAP_DRAWING_POLYGON);
        }
        //编辑区域覆盖物
        function editOverlay() {
            tmpobj = null;
            closeMarker();
            document.getElementById("closeMarker").checked = true;
            drawingManager.close();
            if (overlays.length) {
                for (var i = 0; i < overlays.length; i++) {
                    overlays[i].enableEditing();
                }
            } else {
                alert("没有覆盖物");
            }
        }
        //删除所有区域覆盖物
        function delOverlay() {
            tmpobj = null;
            for (var i = 0; i < overlays.length; i++) {
                map.removeOverlay(overlays[i]);
            }
            overlays.length = 0
        }
        //关闭区域覆盖物操作
        function closeOverlay() {
            tmpobj = null;
            drawingManager.close();
            if (overlays.length) {
                for (var i = 0; i < overlays.length; i++) {
                    overlays[i].disableEditing();
                }
            }
        }

        function setmarker(e) {

            var point = new BMap.Point(e.point.lng, e.point.lat);
            var marker = new BMap.Marker(point);  // 创建标注
            marker.enableDragging(); //设置标注可以拖拽
            map.addOverlay(marker); // 将标注添加到地图中
            var label = new BMap.Label(document.getElementById("marker_title").value, { offset: new BMap.Size(20, -10) });
            label.setTitle(document.getElementById("marker_title").value);
            marker.setLabel(label);
            markers.push(marker);
            document.getElementById("closeMarker").checked = true;
            closeMarker();
            marker.addEventListener("click", function () { tmpobj = this; });
        }

        //新增点覆盖物
        function addMarker() {
            tmpobj = null;
            closeOverlay(); //关闭区域操作
            document.getElementById("closeOverlay").checked = true; //设置区域操作为关闭状态

            map.addEventListener("click", setmarker);

        }
        //关闭点覆盖物
        function closeMarker() {
            tmpobj = null;
            map.removeEventListener("click", setmarker);
        }
        //删除点覆盖物
        function delMarker() {
            tmpobj = null;
            for (var i = 0; i < markers.length; i++) {
                map.removeOverlay(markers[i]);
            }
            markers.length = 0
        }

        //获取默认区域
        function getBoundary() {
            var bdary = new BMap.Boundary();
            bdary.get(document.getElementById("defaultaddress").value, function (rs) {       //获取行政区域      
                var count = rs.boundaries.length; //行政区域的点有多少个
                for (var i = 0; i < count; i++) {
                    var ply = new BMap.Polygon(rs.boundaries[i], {
                        strokeColor: "red",    //边线颜色。
                        fillColor: "blue",      //填充颜色。当参数为空时，圆形将没有填充效果。
                        strokeWeight: 2,       //边线的宽度，以像素为单位。
                        strokeOpacity: 0.8,    //边线透明度，取值范围0 - 1。
                        fillOpacity: 0.2,      //填充的透明度，取值范围0 - 1。
                        strokeStyle: 'solid' //边线的样式，solid或dashed。
                    }); //建立多边形覆盖物
                    map.addOverlay(ply);  //添加覆盖物
                    map.setViewport(ply.getPath());    //调整视野   
                    overlays.push(ply);
                    ply.addEventListener("click", function () { tmpobj = this; });
                }
                if (count == 0) {
                    alert("未查询到相关信息。");
                }
            });
        }

        function getfedault() {
            setTimeout(function () {
                getBoundary();
            }, 1500);
        }

        //保存数据
        function SaveData() {
            var strpoints = "";
            var strcoods = "";

            for (var i = 0; i < overlays.length; i++) {
                if (i != 0) {
                    strcoods += "&";
                }
                var overlaypoints = overlays[i].getPath();
                for (var j = 0; j < overlaypoints.length; j++) {
                    if (j != 0) {
                        strcoods += "|";
                    }
                    strcoods += overlaypoints[j].lng + "," + overlaypoints[j].lat;
                }
            }
            for (var i = 0; i < markers.length; i++) {
                if (i != 0) {
                    strpoints += "|";
                }
                var markerpoint = markers[i].getPosition();
                strpoints += markerpoint.lng + "," + markerpoint.lat + "&" + markers[i].getLabel().getTitle();
            }
            Ext.Ajax.request({
                url: '/Machinery/SaveData',
                params: {
                    mcid: mcid,
                    area: strcoods,
                    point: strpoints,
                    mclevel: mclevel
                },
                async: false,
                success: function (response) {
                    var responseArray = Ext.JSON.decode(response.responseText);
                    if (!responseArray.failed) {
                        MsgTip.msg('操作', '保存成功！', [0, 400], true, 1000);
                        closeOverlay();
                        closeMarker();
                    } else {
                        Ext.Msg.show({
                            title: '错误',
                            msg: '保存失败!',
                            buttons: Ext.MessageBox.OK,
                            icon: Ext.MessageBox.WARNING
                        });
                    }
                }
            });
        }

        //默认展示
        Ext.Ajax.request({
            url: '/Machinery/GetMapData',
            params: {
                mcid: mcid,
                mclevel: mclevel
            },
            async: false,
            success: function (response) {

                var responseArray = Ext.JSON.decode(response.responseText);
                if (!responseArray.failed) {
                    if (responseArray.point != null && responseArray.point != "") {
                        var mypoints = responseArray.point.split("|");
                        for (var i = 0; i < mypoints.length; i++) {
                            var strmypoint = mypoints[i].split("&")[0];
                            var strmylabel = mypoints[i].split("&")[1];
                            var tmppoint = new BMap.Point(strmypoint.split(",")[0], strmypoint.split(",")[1]);
                            var marker = new BMap.Marker(tmppoint);  // 创建标注
                            marker.enableDragging(); //设置标注可以拖拽
                            map.addOverlay(marker); // 将标注添加到地图中
                            var label = new BMap.Label(strmylabel, { offset: new BMap.Size(20, -10) });
                            label.setTitle(strmylabel);
                            marker.setLabel(label);
                            markers.push(marker);
                            marker.addEventListener("click", function () { tmpobj = this; });
                            map.panTo(tmppoint);
                        }
                    }
                    if (responseArray.area != null && responseArray.area != "") {
                        var myareas = responseArray.area.split("&");
                        for (var i = 0; i < myareas.length; i++) {
                            var pointitems = [];
                            var eachpoint = myareas[i].split("|");
                            for (var j = 0; j < eachpoint.length; j++) {
                                var tmppoint = new BMap.Point(eachpoint[j].split(",")[0], eachpoint[j].split(",")[1]);
                                pointitems.push(tmppoint);
                            }
                            var polygon = new BMap.Polygon(pointitems, {
                                strokeColor: "red",    //边线颜色。
                                fillColor: "blue",      //填充颜色。当参数为空时，圆形将没有填充效果。
                                strokeWeight: 2,       //边线的宽度，以像素为单位。
                                strokeOpacity: 0.8,    //边线透明度，取值范围0 - 1。
                                fillOpacity: 0.2,      //填充的透明度，取值范围0 - 1。
                                strokeStyle: 'solid' //边线的样式，solid或dashed。
                            });
                            map.addOverlay(polygon);
                            overlays.push(polygon);
                            //polygon.addEventListener("click", function () { tmpobj = this; });
                        }
                    }
                }
            }
        });

        var upoverlays = [];
        var upmarkers = [];
        var thisoverlays = [];
        var thismarkers = [];
        var downoverlays = [];
        var downmarkers = [];

        var jsondata;

        //获取其他机构信息
        function GetOtherInfo() {
            Ext.Ajax.request({
                url: '../Machinery/GetMapOtherData',
                params: {
                    mcid: mcid,
                    mclevel: mclevel
                },
                async: false,
                success: function (response) {
                    var responseArray = Ext.JSON.decode(response.responseText);
                    if (!responseArray.failed) {
                        jsondata = responseArray;
                    }
                }
            });
        }

        //上级、本级、下级的展示
        function ShowOther(id) {
            var ischeck = document.getElementById(id).checked;
            if (jsondata == null) {
                GetOtherInfo();
            }
            if (id == "uparea") {
                if (ischeck) {
                    if (jsondata.upoverlays != null && jsondata.upoverlays != "") {
                        var myareas = jsondata.upoverlays.split("&");
                        for (var i = 0; i < myareas.length; i++) {
                            var pointitems = [];
                            var eachpoint = myareas[i].split("|");
                            for (var j = 0; j < eachpoint.length; j++) {
                                var tmppoint = new BMap.Point(eachpoint[j].split(",")[0], eachpoint[j].split(",")[1]);
                                pointitems.push(tmppoint);
                            }
                            var polygon = new BMap.Polygon(pointitems, {
                                strokeColor: "red",    //边线颜色。
                                fillColor: "red",      //填充颜色。当参数为空时，圆形将没有填充效果。
                                strokeWeight: 1,       //边线的宽度，以像素为单位。
                                strokeOpacity: 0.8,    //边线透明度，取值范围0 - 1。
                                fillOpacity: 0.3,      //填充的透明度，取值范围0 - 1。
                                strokeStyle: 'solid' //边线的样式，solid或dashed。
                            });
                            map.addOverlay(polygon);
                            upoverlays.push(polygon);
                        }
                    }
                } else {
                    for (var i = 0; i < upoverlays.length; i++) {
                        map.removeOverlay(upoverlays[i]);
                    }
                    upoverlays.length = 0;
                }
            }
            else if (id == "upname") {
                if (ischeck) {
                    if (jsondata.upmarkers != null && jsondata.upmarkers != "") {
                        var mypoints = jsondata.upmarkers.split("|");
                        for (var i = 0; i < mypoints.length; i++) {
                            var strmypoint = mypoints[i].split("&")[0];
                            var strmylabel = mypoints[i].split("&")[1];
                            var tmppoint = new BMap.Point(strmypoint.split(",")[0], strmypoint.split(",")[1]);
                            var marker = new BMap.Marker(tmppoint);
                            map.addOverlay(marker);
                            var label = new BMap.Label(strmylabel, { offset: new BMap.Size(20, -10) });
                            label.setTitle(strmylabel);
                            marker.setLabel(label);
                            upmarkers.push(marker);
                        }
                    }
                } else {
                    for (var i = 0; i < upmarkers.length; i++) {
                        map.removeOverlay(upmarkers[i]);
                    }
                    upmarkers.length = 0;
                }
            }
            else if (id == "thisarea") {
                if (ischeck) {
                    if (jsondata.thisoverlays != null && jsondata.thisoverlays != "") {
                        var allmyareas = eval(jsondata.thisoverlays);
                        for (var t = 0; t < allmyareas.length; t++) {
                            var myareas = allmyareas[t].split("&");
                            for (var i = 0; i < myareas.length; i++) {
                                var pointitems = [];
                                var eachpoint = myareas[i].split("|");
                                for (var j = 0; j < eachpoint.length; j++) {
                                    var tmppoint = new BMap.Point(eachpoint[j].split(",")[0], eachpoint[j].split(",")[1]);
                                    pointitems.push(tmppoint);
                                }
                                var polygon = new BMap.Polygon(pointitems, {
                                    strokeColor: "orange",    //边线颜色。
                                    fillColor: "orange",      //填充颜色。当参数为空时，圆形将没有填充效果。
                                    strokeWeight: 1,       //边线的宽度，以像素为单位。
                                    strokeOpacity: 0.8,    //边线透明度，取值范围0 - 1。
                                    fillOpacity: 0.5,      //填充的透明度，取值范围0 - 1。
                                    strokeStyle: 'solid' //边线的样式，solid或dashed。
                                });
                                map.addOverlay(polygon);
                                thisoverlays.push(polygon);
                            }
                        }
                    }
                } else {
                    for (var i = 0; i < thisoverlays.length; i++) {
                        map.removeOverlay(thisoverlays[i]);
                    }
                    thisoverlays.length = 0;
                }
            }
            else if (id == "thisname") {
                if (ischeck) {
                    if (jsondata.thismarkers != null && jsondata.thismarkers != "") {
                        var allthisnames = eval(jsondata.thismarkers);
                        for (var t = 0; t < allthisnames.length; t++) {
                            var mypoints = allthisnames[t].split("|");
                            for (var i = 0; i < mypoints.length; i++) {
                                var strmypoint = mypoints[i].split("&")[0];
                                var strmylabel = mypoints[i].split("&")[1];
                                var tmppoint = new BMap.Point(strmypoint.split(",")[0], strmypoint.split(",")[1]);
                                var marker = new BMap.Marker(tmppoint);
                                map.addOverlay(marker);
                                var label = new BMap.Label(strmylabel, { offset: new BMap.Size(20, -10) });
                                label.setTitle(strmylabel);
                                marker.setLabel(label);
                                thismarkers.push(marker);
                            }
                        }
                    }
                } else {
                    for (var i = 0; i < thismarkers.length; i++) {
                        map.removeOverlay(thismarkers[i]);
                    }
                    thismarkers.length = 0;
                }
            }
            else if (id == "downarea") {
                if (ischeck) {
                    if (jsondata.downoverlays != null && jsondata.downoverlays != "") {
                        var alldownareas = eval(jsondata.downoverlays);
                        for (var t = 0; t < alldownareas.length; t++) {
                            var downareas = alldownareas[t].split("&");
                            for (var i = 0; i < downareas.length; i++) {
                                var pointitems = [];
                                var eachpoint = downareas[i].split("|");
                                for (var j = 0; j < eachpoint.length; j++) {
                                    var tmppoint = new BMap.Point(eachpoint[j].split(",")[0], eachpoint[j].split(",")[1]);
                                    pointitems.push(tmppoint);
                                }
                                var polygon = new BMap.Polygon(pointitems, {
                                    strokeColor: "blue",    //边线颜色。
                                    fillColor: "blue",      //填充颜色。当参数为空时，圆形将没有填充效果。
                                    strokeWeight: 1,       //边线的宽度，以像素为单位。
                                    strokeOpacity: 0.8,    //边线透明度，取值范围0 - 1。
                                    fillOpacity: 0.2,      //填充的透明度，取值范围0 - 1。
                                    strokeStyle: 'solid' //边线的样式，solid或dashed。
                                });
                                map.addOverlay(polygon);
                                downoverlays.push(polygon);
                            }
                        }
                    }
                } else {
                    for (var i = 0; i < downoverlays.length; i++) {
                        map.removeOverlay(downoverlays[i]);
                    }
                    downoverlays.length = 0;
                }
            }
            else if (id == "downname") {
                if (ischeck) {
                    if (jsondata.downmarkers != null && jsondata.downmarkers != "") {
                        var alldownnames = eval(jsondata.downmarkers);
                        for (var t = 0; t < alldownnames.length; t++) {
                            var mypoints = alldownnames[t].split("|");
                            for (var i = 0; i < mypoints.length; i++) {
                                var strmypoint = mypoints[i].split("&")[0];
                                var strmylabel = mypoints[i].split("&")[1];
                                var tmppoint = new BMap.Point(strmypoint.split(",")[0], strmypoint.split(",")[1]);
                                var marker = new BMap.Marker(tmppoint);
                                map.addOverlay(marker);
                                var label = new BMap.Label(strmylabel, { offset: new BMap.Size(20, -10) });
                                label.setTitle(strmylabel);
                                marker.setLabel(label);
                                downmarkers.push(marker);
                            }
                        }
                    }
                } else {
                    for (var i = 0; i < downmarkers.length; i++) {
                        map.removeOverlay(downmarkers[i]);
                    }
                    downmarkers.length = 0;
                }
            }
        }
    </script>
</body>
</html>