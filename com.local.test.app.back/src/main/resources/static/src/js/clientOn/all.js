// 在线运行页-网吧在线趋势图表
(function() {
    // 获取图表所在div
    var oDiv = document.getElementById('bar_online');
    // 指定图标的配置项
    var barOnlineOption = {
        // 图例组件配置
        legend: {
            data: [{
                name: "当前在线",
                icon: 'circle'
            }, {
                name: "上周同期",
                icon: 'circle'
            }]
        },
        // x轴配置
        xAxis: [{
            data: []
        }],
        // y轴配置
        yAxis: [{
            interval: 25
        }],
        // 系列列表，曲线区域图
        series: [{
            name: '当前在线',
            type: 'line',
            smooth: true,
            symbol: 'none',
            sampling: 'average',
            itemStyle: {
                normal: {
                    color: 'rgb(250, 108, 84)'
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
                        color: 'rgb(155, 82, 78)'
                    }, {
                        offset: 1,
                        color: 'rgb(60, 53, 69)'
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
                    color: 'rgb(66, 255, 191)'
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
                        color: 'rgb(64, 193, 153)'
                    }, {
                        offset: 1,
                        color: 'rgb(5, 37, 60)'
                    }])
                }
            },
            data: []
        }]
    }

    //初始化图表
    var chart = null;
    var renderBarOnline = function() {
        loadData('client_bar.json', function(res) {
            if (chart) {
                // 表格已有，刷新表格
                refreshChart(chart, res.data);
            } else {
                // 表格不存在，创建表格
                barOnlineOption.xAxis[0].data = res.data[0];
                barOnlineOption.series[0].data = res.data[1];
                barOnlineOption.series[1].data = res.data[2];
                chart = renderLineChart(barOnlineOption, oDiv);
            }
        });

    };

    // body绑定事件，触发loadData函数
    $("body").bind("clientReload", function() {
        renderBarOnline();
    });
})();
// 在线运行页-近12小时终端蓝屏率趋势
(function() {
    // 获取图表所在div
    var oDiv = document.getElementById('blue_bar_12h');

    // 指定图标的配置项
    var blueBarOption = {
        // 图例组件配置
        legend: {
            data: [{
                name: "终端",
                icon: 'circle'
            }, {
                name: "上周同期",
                icon: 'circle'
            }]
        },
        // x轴配置
        xAxis: [{
            data: []
        }],
        // y轴配置
        yAxis: [{
            interval: 25
        }],
        // 系列列表，曲线区域图
        series: [{
            name: '终端',
            type: 'line',
            smooth: true,
            symbol: 'none',
            sampling: 'average',
            itemStyle: {
                normal: {
                    color: 'rgb(250, 108, 84)'
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
                        color: 'rgb(155, 82, 78)'
                    }, {
                        offset: 1,
                        color: 'rgb(60, 53, 69)'
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
                    color: 'rgb(66, 255, 191)'
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
                        color: 'rgb(64, 193, 153)'
                    }, {
                        offset: 1,
                        color: 'rgb(5, 37, 60)'
                    }])
                }
            },
            data: []
        }]
    }

    //初始化图表
    var chart = null;
    var renderBlueBar = function() {
        loadData('client_bar.json', function(res) {
            if (chart) {
                // 表格已有，刷新表格
                refreshChart(chart, res.data);
            } else {
                // 表格不存在，创建表格
                blueBarOption.xAxis[0].data = res.data[0];
                blueBarOption.series[0].data = res.data[1];
                blueBarOption.series[1].data = res.data[2];
                chart = renderLineChart(blueBarOption, oDiv);
            }
        });
    };
    // body绑定事件，触发loadData函数
    $("body").bind("clientReload", function() {
        renderBlueBar();
    });
})();
// 在线运行页-近12小时客户端崩溃率趋势
(function() {
    // 获取图表所在div
    var oDiv = document.getElementById('client_crash_12h');

    // 指定图标的配置项
    var clientCrashOption = {
        // 图例组件配置
        legend: {
            data: [{
                name: "当前",
                icon: 'circle'
            }, {
                name: "上周同期",
                icon: 'circle'
            }]
        },
        // x轴配置
        xAxis: [{
            data: []
        }],
        // y轴配置
        yAxis: [{
            interval: 25
        }],
        // 系列列表，曲线区域图
        series: [{
            name: '当前',
            type: 'line',
            smooth: true,
            symbol: 'none',
            sampling: 'average',
            itemStyle: {
                normal: {
                    color: 'rgb(250, 108, 84)'
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
                        color: 'rgb(155, 82, 78)'
                    }, {
                        offset: 1,
                        color: 'rgb(60, 53, 69)'
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
                    color: 'rgb(66, 255, 191)'
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
                        color: 'rgb(64, 193, 153)'
                    }, {
                        offset: 1,
                        color: 'rgb(5, 37, 60)'
                    }])
                }
            },
            data: []
        }]
    }

    //初始化图表
    var chart = null;
    var renderClientCrash = function() {
        loadData("client_bar.json", function(res) {
            if (chart) {
                // 表格已有，刷新表格
                refreshChart(chart, res.data);
            } else {
                // 表格不存在，创建表格
                clientCrashOption.xAxis[0].data = res.data[0];
                clientCrashOption.series[0].data = res.data[1];
                clientCrashOption.series[1].data = res.data[2];
                chart = renderLineChart(clientCrashOption, oDiv);
            }
        });

    };
    // body绑定事件，触发loadData函数
    $("body").bind("clientReload", function() {
        renderClientCrash();
    });
})();
// 在线运行页-全国网维在线终端数
(function() {

    function convertData(data, geoCoordData) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
            var geoCoord = geoCoordData[data[i].name];
            if (geoCoord) {
                res.push({
                    name: data[i].name,
                    value: geoCoord.concat(data[i].value)
                });
            }
        }
        return res;
    };

    var chinaOpts = {
        tooltip: {
            trigger: 'item',
            formatter: function(item) {
                //if (item.seriesType == 'effectScatter')
                return '<div style="padding:5px 8px;">' + item.seriesName +
                    '<br/><span style="vertical-align:top;display:inline-block;width:8px;height:8px;border-radius:50%;margin-right:5px;margin-top:5px;background-color:' +
                    item.color + '"></span>' +
                    item.name + '：<span style="margin-left:5px;">' + item.value[2] + '</span></div>';

                //  else return '{c}';
            }
        },
        legend: {
            show: false
        },
        geo: {
            show: true,
            map: 'china',
            label: {
                normal: {
                    show: false
                },
                emphasis: {
                    show: false
                }
            },
            roam: false,
            itemStyle: {
                normal: {
                    areaColor: 'rgba(255, 255, 255, 0.1)',
                    borderColor: 'rgba(255, 255, 255, 0.4)'
                },
                emphasis: {
                    areaColor: 'rgba(255, 255, 255, 0.1)'
                }
            }
        },
        series: [{
            name: '网维在线终端',
            type: 'effectScatter',
            coordinateSystem: 'geo',
            data: [],
            symbolSize: function(val) {
                var size = val[2] < 1 ? 1 : val[2];
                return size * 3;
            },
            showEffectOn: 'render',
            rippleEffect: {
                brushType: 'stroke'
            },
            hoverAnimation: true,
            label: {
                normal: {
                    formatter: '{b}',
                    position: 'right',
                    show: true
                },
                emphasis: {
                    show: false
                }
            },
            itemStyle: {
                normal: {
                    color: '#3ff5b8',
                    shadowBlur: 10,
                    shadowColor: '#333',
                    opacity: 0.4
                }
            },
            zlevel: 1
        }]
    };

    var chart = null;

    var rendermapChart = function() {
        $.when($.get('./src/js/data/geoCoor.json'), $.get('./src/js/data/bc_num_per_region.json')).then(function(res1, res2) {

            chinaOpts.series[0].data = convertData(res2[0].data, res1[0].data);

            if (!chart) {
                chart = renderMap("china", "client_map", { width: 1000, height: 850 }, chinaOpts);
                animationTooltip(chart);
            } else {
                onlyRefreshChartData(chart, convertData(res2[0].data, res1[0].data));
            }
        });

    }

    rendermapChart();

    //通过clientReload事件定时去刷新页面数据
    $('body').bind('clientReload', function() {
        rendermapChart();
    });

})();
// 在线运行页-终端在线趋势
(function() {
    // 获取图表所在div
    var oDiv = document.getElementById('client_online');

    // 指定图标的配置项
    var clientOnlineOption = {
        // 图例组件配置
        legend: {
            data: [{
                name: "当前在线",
                icon: 'circle'
            }, {
                name: "上周同期",
                icon: 'circle'
            }]
        },
        // x轴配置
        xAxis: [{
            data: []
        }],
        // y轴配置
        yAxis: [{
            interval: 25
        }],
        // 系列列表，曲线区域图
        series: [{
            name: '当前在线',
            type: 'line',
            smooth: true,
            symbol: 'none',
            sampling: 'average',
            itemStyle: {
                normal: {
                    color: 'rgb(250, 108, 84)'
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
                        color: 'rgb(155, 82, 78)'
                    }, {
                        offset: 1,
                        color: 'rgb(60, 53, 69)'
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
                    color: 'rgb(66, 255, 191)'
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
                        color: 'rgb(64, 193, 153)'
                    }, {
                        offset: 1,
                        color: 'rgb(5, 37, 60)'
                    }])
                }
            },
            data: []
        }]
    }

    //初始化图表 
    var chart = null;
    var renderClientOnlineTrend = function() {
        loadData("client_online_trend.json", function(res) {
            if (chart) {
                // 表格已有，刷新表格
                refreshChart(chart, res.data);
            } else {
                // 表格不存在，创建表格
                clientOnlineOption.xAxis[0].data = res.data[0];
                clientOnlineOption.series[0].data = res.data[1];
                clientOnlineOption.series[1].data = res.data[2];
                chart = renderLineChart(clientOnlineOption, oDiv);
            }
        })

    };
    // body绑定事件，触发loadData函数
    $("body").bind("clientReload", function() {
        renderClientOnlineTrend();
    });

})();
/** 
 * @requires echarts-12h-baronline.js
 * @requires echarts-12h-blue.js
 * @requires echarts-12h-clientcrash.js
 * @requires echarts-map.js
 * @requires echarts-clientonline.js**/