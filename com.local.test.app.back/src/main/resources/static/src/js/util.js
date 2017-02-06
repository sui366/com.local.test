    var basePath = "./src/js/data/";

    //自动将数字格式化为"#,##,##"
    Number.prototype.format = function() {
        return this.toLocaleString();
    }

    //将小数转换为百分比
    Number.prototype.toPercentage = function() {
            return (this * 100).toFixed(2) + '%';
        }
        // 地图tooltip自动切换
    function animationTooltip(chart, animationDelay) {
        var app = {};
        app.currentIndex = -1;
        animationDelay = animationDelay || 2000;

        setInterval(function() {
            var dataLen = chart.getOption().series[0].data.length;
            // 取消之前高亮的图形
            chart.dispatchAction({
                type: 'downplay',
                seriesIndex: 0,
                dataIndex: app.currentIndex
            });
            app.currentIndex = (app.currentIndex + 1) % dataLen;
            // 高亮当前图形
            chart.dispatchAction({
                type: 'highlight',
                seriesIndex: 0,
                dataIndex: app.currentIndex
            });
            // 显示 tooltip
            chart.dispatchAction({
                type: 'showTip',
                seriesIndex: 0,
                dataIndex: app.currentIndex
            });
        }, animationDelay);
    }
    // 获取数据
    function loadData(urlPath, postData, callback) {
        if (typeof postData == "function") {
            callback = postData;
        }

        var opts = {
            url: basePath + urlPath,
            dataType: 'json',
            method: 'GET',
            success: function(res) {
                if (!res) return;
                if (callback)
                    callback(res);
            }
        };

        if (typeof postData == 'object') {
            opts["method"] = "POST";
            opts["data"] = postData;
        }

        $.ajax(opts);
    }
    // 绘制地图
    function renderMap(mapType, container, mapSize, opts) {
        console.log(container);
        var oDiv = document.getElementById(container);
        //用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
        var resizeWorldMapContainer = function() {
            oDiv.style.width = mapSize.width + 'px';
            oDiv.style.height = mapSize.height + 'px';
        };
        //设置容器高宽
        resizeWorldMapContainer();
        var myChart = echarts.init(oDiv);
        // 注册地图
        $.get('./mapData/' + mapType + '.json', function(res) {
            echarts.registerMap(mapType, res);
            myChart.setOption(opts);
        });

        return myChart;
    }
    // 格式化时间
    function getFormatterSystemDatetime(date) {
        return moment(date).format("LL dddd A hh:mm:ss");
    }
    //定时去刷新页面
    function refreshPage() {
        setInterval(function() {
            if (moment().format("HH:mm:ss") == "08:00:00") {
                window.location.reload();
            }
        }, 1000);

    }