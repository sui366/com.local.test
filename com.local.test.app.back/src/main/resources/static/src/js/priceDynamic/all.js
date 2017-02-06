//默认首页、主题动画、卡牌以及图标排位：昨日今日数据的渲染与比对
function renderBaseInfo(el, data) {
    $(".itemTotalYesterday", $(el)).html(data.itemTotalYesterday.format());
    $(".itemTotalCurrent", $(el)).html(data.itemTotalCurrent.format());


    var statusEl = $(".pv_status", $(el))
    if (data.itemTotalCurrent > data.itemTotalYesterday) {
        statusEl.addClass('pc-today-up');
        statusEl.removeClass('pc-today-down');
    } else if (data.itemTotalCurrent < data.itemTotalYesterday) {
        statusEl.addClass('pc-today-down');
        statusEl.removeClass('pc-today-up')
    } else {
        elem.removeClass('pc-today-down');
        elem.removeClass('pc-today-up');
    }
}

(function() {

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

    var loadSystemTime = function() {
        //加载服务器时间
        lastCalibrationTime = moment(new Date());
        renderSystemDatetime(lastCalibrationTime);
    }

    loadSystemTime();

    //定制发起数据请求事件
    var loadHandler = null;
    var isInit = true;
    var nextLoadTime = 0;
    var loadDataMonitor = function() {
        if (loadHandler)
            clearInterval(loadHandler);

        loadHandler = setInterval(function() {
            $("body").trigger("commercialValueReload");
            //每5分钟请求一次数据
            //nextLoadTime = 300000;
            //测试数30s
            nextLoadTime = 30000;
            if (isInit) {
                //初始化之后需要更新下一次请求的时间
                loadDataMonitor();
                isInit = false;
            }
        }, nextLoadTime);

    }
    loadDataMonitor();

    var calBarWidth = function(data1, data2) {
        var width = data1 / data2;
        width = width > 1 ? 1 : width;
        return width.toPercentage();
    }

    var renderHtml = function(el, datas) {
        $(el).html(" ");
        $.each(datas, function(index, item) {
            $(el).append('<tr><td>' + item.downloadTime + '</td><td>' + item.version + '</td><td><div class="progress-bg"><div class="progress-bar"><div>' + item.downloadBarNum + '</div></div></div></td></tr>');
            var $tr = $("tr", $(el))[index];
            setTimeout(function() {
                $(".progress-bar", $tr).css({ "width": calBarWidth(item.downloadBarNum, item.destBarNum) });
            })
        });
    };

    // 加载广告资源到达情况
    function renderAdsSourceInfo() {
        loadData("ad_source.json", function(res) {

            var result = res.data;
            //在线网吧数
            $("#online_bar").html(result.onlineBarNum.format());

            var top5Items = _.take(result.downloadInfoDetails, 5);
            var last5Items = _.takeRight(result.downloadInfoDetails, 5);

            renderHtml($("table>tbody", $(".pc-banner-left")), top5Items);
            renderHtml($("table>tbody", $(".pc-banner-right")), last5Items);
        });
    }

    $("body").bind('commercialValueReload', function() {
        renderAdsSourceInfo();
    });

    //定时去刷新页面为了防止一直运行页面而导致CPU使用率过高的问题
    refreshPage();
})();
(function() {
    // 获取图表所在div
    var oDiv = document.getElementById('pc_bc');
    // 指定图标的配置项
    var bcOption = {
        // 图表标题
        title: {
            text: '近12小时终端在线趋势（万）',
            textStyle: {
                color: '#798da8',
                fontWeight: 'normal',
                fontSize: '12px'
            },
            left: 60,
            top: 24
        },
        // 鼠标 hover 提示
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                lineStyle: {
                    color: 'rgba(0,0,0,0)'
                }
            }
        },
        // 坐标系网格配置
        grid: {
            left: 65,
            top: 61,
            right: 63,
            bottom: 30,
            containLabel: true
        },
        // x轴配置
        xAxis: [{
            type: 'category',
            axisLine: {
                show: false
            },
            axisTick: {
                show: false,
                alignWithLabel: true
            },
            axisLabel: {
                margin: 16,
                textStyle: {
                    color: '#798da8'
                }
            },
            data: []
        }],
        // y轴配置
        yAxis: [{
            type: 'value',
            interval: 70,
            splitNumber: 4,
            axisLine: {
                show: false
            },
            axisTick: {
                show: false,
                alignWithLabel: true
            },
            splitLine: {
                lineStyle: {
                    color: '#37526b'
                }
            },
            axisLabel: {
                margin: 22,
                textStyle: {
                    color: '#798da8'
                },
            formatter: '{value}'
            }
        }],
        // 系列列表，曲线区域图
        series: [{
            name: '在线',
            type: 'line',
            symbol: 'emptyCircle',
            symbolSize: 17,
            itemStyle: {
                normal: {
                    color: 'rgb(109, 212, 121)'
                }
            },
            lineStyle: {
                normal: {
                    width: 3
                }
            },
            data: []
        }]
    }

    //初始化图表
    var chart = null;
    var renderBlueBar = function() {
        loadData('price_bc_trend.json', function(res) {
            if (chart) {
                // 表格已有，刷新表格
                refreshChart(chart, res.data);
            } else {
                // 表格不存在，创建表格
                bcOption.xAxis[0].data = res.data[0];
                bcOption.series[0].data = res.data[1];
                chart = renderLineChart(bcOption, oDiv);
				animationTooltip(chart);
            }
            $('.bc-pc-ratio').html(res.ratio);
        });
    };
    // body绑定事件，触发loadData函数
    $("body").bind("commercialValueReload", function() {
        renderBlueBar();
    });
})();
(function() {
    // 获取图表所在div
    var oDiv = document.getElementById('pc_pv');
    // 指定图标的配置项
    var pvOption = {
        // 鼠标 hover 提示
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                lineStyle: {
                    color: 'rgba(0,0,0,0)'
                }
            }
        },
        // 图例组件配置
        legend: {
            data: [{
                name: "今日",
                icon: 'circle'
            }, {
                name: "昨日",
                icon: 'circle'
            }],
            align: 'left',
            top: 39,
            right: 65,
            itemGap: 21,
            textStyle: {
                color: '#798da8'
            }
        },
        // 坐标系网格配置
        grid: {
            left: 110,
            top: 98,
            right: 63,
            bottom: 70,
            containLabel: false
        },
        // x轴配置
        xAxis: [{
            type: 'category',
            boundaryGap: true,
            axisLine: {
                show: true
            },
            axisTick: {
                show: false,
                alignWithLabel: true
            },
            axisLabel: {
                margin: 19,
                textStyle: {
                    color: '#798da8'
                }
            },
            data: []
        }],
        // y轴配置
        yAxis: [{
            type: 'value',
            min: 92,
            max: 100,
            interval: 2,
            splitNumber: 4,
            axisLine: {
                show: false
            },
            axisTick: {
                show: false,
                alignWithLabel: true
            },
            splitLine: {
                show: false
            },
            axisLabel: {
                margin: 16,
                textStyle: {
                    color: '#798da8'
                },
                formatter: '{value}%'
            }
        }],
        // 系列列表，曲线区域图
        series: [{
                name: '昨日',
                type: 'bar',
                data: [],
                itemStyle: {
                    normal: {
                        color: '#187efe'
                    }
                },
                barWidth: 32
            },
            {
                name: '今日',
                type: 'bar',
                data: [],
                itemStyle: {
                    normal: {
                        color: '#6dd479'
                    }
                },
                barWidth: 32
            }
        ]
    }

    //初始化图表
    var chart = null;
    var renderBlueBar = function() {
        loadData('price_pv_normal.json', function(res) {
            if (chart) {
                // 表格已有，刷新表格
                refreshChart(chart, res.data);
            } else {
                // 表格不存在，创建表格
                pvOption.xAxis[0].data = res.data[0];
                pvOption.series[0].data = res.data[1];
                pvOption.series[1].data = res.data[2];
                chart = renderLineChart(pvOption, oDiv);
                //animationTooltip(chart);
            }
        });
    };
    // body绑定事件，触发loadData函数
    $("body").bind("commercialValueReload", function() {
        renderBlueBar();
    });
})();
// 商业价值动态-默认首页pv-图表
(function() {
    // 获取图表所在div
    var oDiv = document.getElementById('default_pv');
    // 指定图标的配置项
    var priceOption = {
        // 图表标题
        title: {
            text: '近12小时每百万开机趋势',
            textStyle: {
                color: '#798da8',
                fontWeight: 'normal',
                fontSize: '12px'
            },
            left: 27,
            top: 23
        },
        // 图例组件配置
        legend: {
            data: [{
                name: "最近",
                icon: 'circle'
            }, {
                name: "上周同期",
                icon: 'circle'
            }],
            align: 'left',
            top: 29,
            right: 30,
            itemGap: 34,
            textStyle: {
                color: '#798da8'
            }
        },
        // x轴配置
        xAxis: [{
            data: []
        }],
        // y轴配置
        yAxis: [{
            type: 'value',
            interval: 4000,
            splitNumber: 4,
            axisLine: {
                show: false
            },
            axisTick: {
                show: false,
                alignWithLabel: true
            },
            splitLine: {
                lineStyle: {
                    color: '#37526b'
                }
            },
            axisLabel: {
                margin: 17,
                textStyle: {
                    color: '#798da8'
                },
                formatter: '{value}'
            }
        }],
        // 系列列表，曲线区域图
        series: [{
            name: '最近',
            type: 'line',
            smooth: true,
            symbol: 'none',
            sampling: 'average',
            itemStyle: {
                normal: {
                    color: 'rgb(109, 212, 121)'
                }
            },
            lineStyle: {
                normal: {
                    width: 3
                }
            },
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgb(45, 95, 74)'
                    }, {
                        offset: 1,
                        color: 'rgb(10, 25, 41)'
                    }])
                }
            },
            data: []
        }, {
            name: '上周同期',
            type: 'line',
            smooth: true,
            symbol: 'none',
            sampling: 'average',
            itemStyle: {
                normal: {
                    color: 'rgb(24, 126, 254)'
                }
            },
            lineStyle: {
                normal: {
                    width: 3
                }
            },
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgb(19, 68, 123)'
                    }, {
                        offset: 1,
                        color: 'rgb(10, 18, 32)'
                    }])
                }
            },
            data: []
        }]
    }

    //初始化图表
    var chart = null;
    var renderBlueBar = function() {
        loadData('commercial_val_info.json', function(res) {
            if (chart) {
                // 表格已有，刷新表格
                refreshChart(chart, res.data.details);
            } else {
                // 表格不存在，创建表格
                priceOption.xAxis[0].data = res.data.details[0];
                priceOption.series[0].data = res.data.details[1];
                priceOption.series[1].data = res.data.details[2];
                chart = renderLineChart(priceOption, oDiv);
                animationTooltip(chart);
            }
            //加载昨日以及今日数据
            renderBaseInfo($(".default_pv"), res.data);
        });
    };
    // body绑定事件，触发loadData函数
    $("body").bind("commercialValueReload", function() {
        renderBlueBar();
    });
})();
// 商业价值动态-主题动画pc-图表
(function() {
    // 获取图表所在div
    var oDiv = document.getElementById('cartoon_pv');
    // 指定图标的配置项
    var priceOption = {
        // 图表标题
        title : {
            text : '近12小时每百万开机趋势',
            textStyle : {
                color : '#798da8',
                fontWeight : 'normal',
                fontSize : '12px'
            },
            left : 27,
            top : 23
        },
        // 图例组件配置
        legend: {
            data: [{
                name: "最近",
                icon: 'circle'
            }, {
                name: "上周同期",
                icon: 'circle'
            }],
            align : 'left',
            top : 29,
            right : 30,
            itemGap : 34,
            textStyle : {
                color : '#798da8'
            }
        },
        // x轴配置
        xAxis: [{
            data: []
        }],
        // y轴配置
        yAxis: [{
            type: 'value',
            interval: 4000,
            splitNumber: 4,
            axisLine: {
                show: false
            },
            axisTick: {
                show: false,
                alignWithLabel: true
            },
            splitLine: {
                lineStyle: {
                    color: '#37526b'
                }
            },
            axisLabel: {
                margin: 17,
                textStyle: {
                    color: '#798da8'
                },
            formatter: '{value}'
            }
        }],
        // 系列列表，曲线区域图
        series: [{
            name: '最近',
            type: 'line',
            smooth: true,
            symbol: 'none',
            sampling: 'average',
            itemStyle: {
                normal: {
                    color: 'rgb(109, 212, 121)'
                }
            },
            lineStyle: {
                normal: {
                    width: 3
                }
            },
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgb(45, 95, 74)'
                    }, {
                        offset: 1,
                        color: 'rgb(10, 25, 41)'
                    }])
                }
            },
            data: []
        }, {
            name: '上周同期',
            type: 'line',
            smooth: true,
            symbol: 'none',
            sampling: 'average',
            itemStyle: {
                normal: {
                    color: 'rgb(24, 126, 254)'
                }
            },
            lineStyle: {
                normal: {
                    width: 3
                }
            },
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgb(19, 68, 123)'
                    }, {
                        offset: 1,
                        color: 'rgb(10, 18, 32)'
                    }])
                }
            },
            data: []
        }]
    }

    //初始化图表
    var chart = null;
    var renderBlueBar = function() {
        loadData('commercial_val_info.json', function(res) {
            if (chart) {
                // 表格已有，刷新表格
                refreshChart(chart, res.data.details);
            } else {
                // 表格不存在，创建表格
                priceOption.xAxis[0].data = res.data.details[0];
                priceOption.series[0].data = res.data.details[1];
                priceOption.series[1].data = res.data.details[2];
                chart = renderLineChart(priceOption, oDiv);
                animationTooltip(chart);
            }
            //加载昨日以及今日数据
            renderBaseInfo($(".cartoon_pc"), res.data);
        });
    };
    // body绑定事件，触发loadData函数
    $("body").bind("commercialValueReload", function() {
        renderBlueBar();
    });
})();
// 商业价值动态-卡牌pc-图表
(function() {
    // 获取图表所在div
    var oDiv = document.getElementById('card_pc');

    // 指定图标的配置项
    var priceOption = {
        // 图表标题
        title: {
            text: '近12小时每百万开机趋势',
            textStyle: {
                color: '#798da8',
                fontWeight: 'normal',
                fontSize: '12px'
            },
            left: 27,
            top: 23
        },
        // 图例组件配置
        legend: {
            data: [{
                name: "最近",
                icon: 'circle'
            }, {
                name: "上周同期",
                icon: 'circle'
            }],
            align: 'left',
            top: 29,
            right: 30,
            itemGap: 34,
            textStyle: {
                color: '#798da8'
            }
        },
        // x轴配置

        xAxis: [{
            data: []
        }],
        // y轴配置
        yAxis: [{
            type: 'value',
            interval: 4000,
            splitNumber: 4,
            axisLine: {
                show: false
            },
            axisTick: {
                show: false,
                alignWithLabel: true
            },
            splitLine: {
                lineStyle: {
                    color: '#37526b'
                }
            },
            axisLabel: {
                margin: 17,
                textStyle: {
                    color: '#798da8'
                },
                formatter: '{value}'
            }
        }],
        // 系列列表，曲线区域图
        series: [{
            name: '最近',
            type: 'line',
            smooth: true,
            symbol: 'none',
            sampling: 'average',
            itemStyle: {
                normal: {
                    color: 'rgb(109, 212, 121)'
                }
            },
            lineStyle: {
                normal: {
                    width: 3
                }
            },
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgb(45, 95, 74)'
                    }, {
                        offset: 1,
                        color: 'rgb(10, 25, 41)'
                    }])
                }
            },
            data: []
        }, {
            name: '上周同期',
            type: 'line',
            smooth: true,
            symbol: 'none',
            sampling: 'average',
            itemStyle: {
                normal: {
                    color: 'rgb(24, 126, 254)'
                }
            },
            lineStyle: {
                normal: {
                    width: 3
                }
            },
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgb(19, 68, 123)'
                    }, {
                        offset: 1,
                        color: 'rgb(10, 18, 32)'
                    }])
                }
            },
            data: []
        }]
    }

    //初始化图表
    var chart = null;
    var renderBlueBar = function() {
        loadData('commercial_val_info.json', function(res) {
            if (chart) {
                // 表格已有，刷新表格
                refreshChart(chart, res.data.details);
            } else {
                // 表格不存在，创建表格
                priceOption.xAxis[0].data = res.data.details[0];
                priceOption.series[0].data = res.data.details[1];
                priceOption.series[1].data = res.data.details[2];
                chart = renderLineChart(priceOption, oDiv);
                animationTooltip(chart);
            }
            //加载昨日以及今日数据
            renderBaseInfo($(".card_pc"), res.data);
        });
    };
    // body绑定事件，触发loadData函数
    $("body").bind("commercialValueReload", function() {
        renderBlueBar();
    });
})();
// 商业价值动态-图标排位pc-图表
(function() {
    // 获取图表所在div
    var oDiv = document.getElementById('icon_pv');
    // 指定图标的配置项
    var priceOption = {
        // 图表标题
        title : {
            text : '近12小时每百万开机趋势',
            textStyle : {
                color : '#798da8',
                fontWeight : 'normal',
                fontSize : '12px'
            },
            left : 27,
            top : 23
        },
                // 图例组件配置
        legend : {
            data : [{
                name : "最近",
                icon : 'circle'
            },{
                name : "上周同期",
                icon : 'circle'
            }],
            align : 'left',
            top : 29,
            right : 30,
            itemGap : 34,
            textStyle : {
                color : '#798da8'
            }
        },
        // x轴配置
        xAxis: [{
            data: []
        }],
        // y轴配置
        yAxis: [{
            type: 'value',
            interval: 4000,
            splitNumber: 4,
            axisLine: {
                show: false
            },
            axisTick: {
                show: false,
                alignWithLabel: true
            },
            splitLine: {
                lineStyle: {
                    color: '#37526b'
                }
            },
            axisLabel: {
                margin: 17,
                textStyle: {
                    color: '#798da8'
                },
            formatter: '{value}'
            }
        }],
        // 系列列表，曲线区域图
        series: [{
            name: '最近',
            type: 'line',
            smooth: true,
            symbol: 'none',
            sampling: 'average',
            itemStyle: {
                normal: {
                    color: 'rgb(109, 212, 121)'
                }
            },
            lineStyle: {
                normal: {
                    width: 3
                }
            },
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgb(45, 95, 74)'
                    }, {
                        offset: 1,
                        color: 'rgb(10, 25, 41)'
                    }])
                }
            },
            data: []
        }, {
            name: '上周同期',
            type: 'line',
            smooth: true,
            symbol: 'none',
            sampling: 'average',
            itemStyle: {
                normal: {
                    color: 'rgb(24, 126, 254)'
                }
            },
            lineStyle: {
                normal: {
                    width: 3
                }
            },
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgb(19, 68, 123)'
                    }, {
                        offset: 1,
                        color: 'rgb(10, 18, 32)'
                    }])
                }
            },
            data: []
        }]
    }

    //初始化图表
    var chart = null;
    var renderBlueBar = function() {
        loadData('commercial_val_info.json', function(res) {
            if (chart) {
                // 表格已有，刷新表格
                refreshChart(chart, res.data.details);
            } else {
                // 表格不存在，创建表格
                priceOption.xAxis[0].data = res.data.details[0];
                priceOption.series[0].data = res.data.details[1];
                priceOption.series[1].data = res.data.details[2];
                chart = renderLineChart(priceOption, oDiv);
                animationTooltip(chart);
            }
            //加载昨日以及今日数据
            renderBaseInfo($(".icon_pv"), res.data);
        });
    };
    // body绑定事件，触发loadData函数
    $("body").bind("commercialValueReload", function() {
        renderBlueBar();
    });
})();
/** 
 * @requires base.js
 * @requires echarts-pc-bc.js
 * @requires echarts-pc-pv.js
 * @requires echarts-default-pv.js
 * @requires echarts-cartoon-pc.js
 * @requires echarts-card-pc.js
 * @requires echarts-icon-pc.js**/