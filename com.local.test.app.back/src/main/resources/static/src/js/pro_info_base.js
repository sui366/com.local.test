// bc-表示蓝屏终端
// cc-表示崩溃终端
// oc-表示在线运行终端
(function() {

    // 页面轮播-蓝屏终端、崩溃终端、在线运行终端
    function pageSwitch(el, panels) {
        var Slide = $(el).switchable({
            putTriggers: 'appendTo',
            panels: panels,
            effect: 'fade', // taking effect when autoplay == true
            end2end: true, // if set to true, loop == true
            loop: true, // not taking effect, because end2end == true
            autoplay: true,
            interval: 15, //以秒为单位5min=300测试的时候使用15s,
            api: true // if set to true, Switchable returns API
        });
    }

    function renderCityRow(data) {
        // 补丁升级数据中城市数据拼接
        var items = data.split("，");
        var html = [];
        $.each(items, function(index, item) {

            var separator = (index + 1) % 2 == 0 ? "<br/>" : "&nbsp;";
            html.push(item + separator);
        });

        return html.join("");
    }

    function renderTRHtml(datas) {
        // 表格tr拼接
        var html = [];
        $.each(datas, function(index, item) {
            html.push("<tr>");
            html.push("<td>" + item["updateTime"] + "</td>");
            html.push("<td>" + item["channel"] + "</td>");
            html.push("<td><span>" + renderCityRow(item["cityName"]) + "</span></td>");
            html.push("<td>" + item["barNum"] + "</td>");
            html.push("<td>" + item["clientNum"] + "</td>");
            html.push("</tr>");
        });

        return html.join(" ");
    }
    // 时间处理
    moment.locale('zh-cn');

    var timerHandler = null;
    var lastCalibrationTime = new moment();
    var renderSystemDatetime = function(datetime) {

        if (timerHandler)
            clearInterval(timerHandler);

        timerHandler = setInterval(function() {
            datetime = moment(datetime).add(1, 'seconds');
            $(".time-now").html(getFormatterSystemDatetime(datetime));
            refreshSystemDatetime();
        }, 1000);
    }

    //每个小时校准一次时间
    var refreshSystemDatetime = function() {
        setInterval(function() {
            if (moment(new Date()).hour() != lastCalibrationTime.hour()) {
                loadSystemTime();
            }
        }, 10000)
    }

    //加载服务器时间
    var loadSystemTime = function() {
        // loadData("",function(res){

        // });
        lastCalibrationTime = moment(new Date());
        renderSystemDatetime(lastCalibrationTime);
    }

    //定制发起数据请求事件
    // 五分钟请求一次数据图表
    var loadHandler = null;
    var isInit = true;
    var nextLoadTime = 0;
    var loadDataMonitor = function() {
        if (loadHandler)
            clearInterval(loadHandler);

        loadHandler = setInterval(function() {
            $("body").trigger("clientReload");
            //每5分钟请求一次数据
            //nextLoadTime = 300000;
            //测试数1min
            nextLoadTime = 60000;
            if (isInit) {
                //初始化之后需要更新下一次请求的时间
                isInit = false;
            }
            loadDataMonitor();
        }, nextLoadTime);

    }

    // 绘制补丁升级表格方法 
    function c_loadPatchUpdateRecord(URL, IDL, IDR) {
        loadData(URL, function(res) {
            $("table>tbody", $(IDL)).html(renderTRHtml(_.take(res.data, 5)));
            $("table>tbody", $(IDR)).html(renderTRHtml(_.takeRight(res.data, 5)));
        });
    }

    function displayDynamic(el, fromValue, toValue) {
        $(el).numerator({
            duration: 3000,
            fromValue: fromValue,
            toValue: toValue,
            delimiter: ","
        });
    }

    var scrollEffectItems = ["ccNum", "bcNum", "ocNum"];
    var prevClientNum = {};
    var isPageInited = true;
    // 加载动态数据到各个终端页面方法
    function c_loadBaseInfo(ID) {

        loadData("bc_info.json", function(res) {
            var result = res.data;

            // 根据数据比较，插入上下箭头
            var addRemoveClass = function(key1, key2, elem) {
                if (result[key1] > result[key2]) {
                    elem.addClass('all-online-up');
                    elem.removeClass('all-online-down');
                } else if (result[key1] < result[key2]) {
                    elem.addClass('all-online-down');
                    elem.removeClass('all-online-up')
                } else {
                    elem.removeClass('all-online-down');
                    elem.removeClass('all-online-up');
                }
            }
            addRemoveClass("ocNum", "ocNumYesterday", $('.ocNum'));
            addRemoveClass("ccNum", "ccNumYesterday", $('.ccNum'));
            addRemoveClass("bcNum", "bcNumYesterday", $('.bcNum'));

            // 页面插入数据

            $.each(result, function(key, value) {
                if (scrollEffectItems.indexOf(key) > -1) {
                    //蓝屏终端数，在线终端数，崩溃终端数动态效果
                    var fromValue = !prevClientNum[key] ? (value - (value / 1000)) : prevClientNum[key];
                    displayDynamic($("." + key, $(ID)), fromValue, value);
                } else {
                    var value = (typeof value == "number" && key.toUpperCase().indexOf('PCT') == -1) ? value.format() : (key.toUpperCase().indexOf('PCT') > -1 && (typeof value == 'number')) ? value.toPercentage() : value;
                    $("." + key, $(ID)).html(value);
                }
            });

            if (isPageInited) {
                $(".page-spinner-bar").hide();
                $("#body_container").show();

                pageSwitch("#body_container", 'section');
            }
            isPageInited = false;
        });
    }

    var initPage = function() {
        //加载服务器时间
        loadSystemTime();

        // 加载动态数据到蓝屏终端页面
        c_loadBaseInfo("#blue_screen");

        // 加载动态数据到崩溃终端页面
        c_loadBaseInfo("#clinet_crash");

        // 加载动态数据到在线运行终端页面
        c_loadBaseInfo("#client_on");

        // 绘制蓝屏终端页面补丁升级表格
        c_loadPatchUpdateRecord("patch_record.json", "#blue_screen .patch-left", "#blue_screen .patch-right");

        // 绘制崩溃终端页面补丁升级表格
        c_loadPatchUpdateRecord("patch_record.json", "#clinet_crash .patch-left", "#clinet_crash .patch-right");

    }

    //执行页面监听事件
    loadDataMonitor();
    //定时去刷新页面为了防止一直运行页面而导致CPU使用率过高的问题
    refreshPage();
    $("body").bind('clientReload', function() {
        initPage();
    });

})();