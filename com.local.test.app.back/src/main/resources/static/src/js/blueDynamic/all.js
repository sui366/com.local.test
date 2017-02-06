// 蓝屏终端页-蓝屏网吧尾号分布情况
(function() {
    // 获取图表所在div
    var oDiv = document.getElementById('blue_bar_code');
    // 近12小时
    var xaxis = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9];

    // 指定图标的配置项
    var blueBarOption = {
        // 图例组件配置
        legend: {
            data: [{
                name: "蓝屏网吧数",
                icon: 'roundRect'
            }]
        },
        // x轴配置
        xAxis: [{
            data: xaxis
        }],
        // y轴配置
        yAxis: [{
            interval: 250
        }],
        // 系列列表，曲线区域图
        series: [{
            name: '蓝屏网吧数',
            type: 'bar',
            barWidth: 16,
            markLine: {
                data: [{
                    name: '平均值',
                    type: 'average'
                }],
                symbol: 'none',
                label: {
                    normal: {
                        show: false
                    }
                },
                lineStyle: {
                    normal: {
                        color: '#9d6bc0',
                        width: 2,
                        type: 'solid',
                        shadowColor: '#9d6bc0',
                        shadowBlur: 10,
                        shadowOffsetY: 2
                    }
                }
            },
            data: []
        }]
    }

    //初始化图表
    var chart = null;
    var renderBlueScreenBarTailNumber = function() {
        loadData('bar_tail_num.json', function(res) {
            // 判断图表是否存在，如果不存在渲染一个，如果已经存在刷新数据
            if (chart) {
                onlyRefreshChartData(chart, res.data);
            } else {
                blueBarOption.series[0].data = res.data;
                chart = renderBarChart(blueBarOption, oDiv);
            }
        })

    }

    // body绑定事件，触发renderBlueScreenBarTailNumber函数
    $("body").bind("clientReload", function() {
        renderBlueScreenBarTailNumber();
    });
})();
// 蓝屏终端页-蓝屏终端尾号分布情况
(function() {
    // 获取图表所在div
    var oDiv = document.getElementById('blue_pc_code');
    // 近12小时
    var xaxis = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9];
    // 指定图标的配置项
    var bluePcOption = {
        // 图例组件配置
        legend: {
            data: [{
                name: "蓝屏终端数",
                icon: 'roundRect'
            }]
        },
        // x轴配置
        xAxis: [{
            data: xaxis
        }],
        // y轴配置
        yAxis: [{
            interval: 2000
        }],
        // 系列列表，曲线区域图
        series: [{
            name: '蓝屏终端数',
            type: 'bar',
            barWidth: 16,
            markLine: {
                data: [{
                    name: '平均值',
                    type: 'average'
                }],
                symbol: 'none',
                label: {
                    normal: {
                        show: false
                    }
                },
                lineStyle: {
                    normal: {
                        color: '#9d6bc0',
                        width: 2,
                        type: 'solid',
                        shadowColor: '#9d6bc0',
                        shadowBlur: 10,
                        shadowOffsetY: 2
                    }
                }
            },
            data: []
        }]
    }

    //初始化图表
    var chart = null;
    var renderBlueScreenClientTailNumber = function() {
        loadData('bar_tail_num.json', function(res) {
            // 判断图表是否存在，如果不存在渲染一个，如果已经存在刷新数据
            if (chart) {
                onlyRefreshChartData(chart, res.data);
            } else {
                bluePcOption.series[0].data = res.data;
                chart = renderBarChart(bluePcOption, oDiv);
            }
        })

    }

    //renderBlueScreenClientTailNumber();
    // body绑定事件，触发loadData函数
    $("body").bind("clientReload", function() {
        renderBlueScreenClientTailNumber();
    });
})();
// 蓝屏终端页-客户端蓝屏代码TOP10
(function() {
    // 获取图表所在div
    var top = echarts.init(document.getElementById('blue_top10'));

    // 指定图标的配置项
    var topOption = {
        tooltip: {},
        series: [{
            type: 'wordCloud',
            gridSize: 20,
            sizeRange: [12, 34],
            rotationRange: [0, 0],
            shape: 'circle',
            textPadding: 0,
            autoSize: {
                enable: true,
                minSize: 14
            },
            textStyle: {
                normal: {
                    color: function() {
                        return 'rgb(' + [
                            Math.round(Math.random() * 160),
                            Math.round(Math.random() * 160),
                            Math.round(Math.random() * 160)
                        ].join(',') + ')';
                    }
                },
                emphasis: {
                    shadowBlur: 10,
                    shadowColor: '#333'
                }
            },
            data: []
        }]
    };


    var chart = null;
    var renderBlueCode = function() {
        loadData('blue_screen_code.json', function(res) {
            if (chart)
                onlyRefreshChartData(chart, res.data)
            else {
                topOption.series[0].data = res.data;
                chart = top.setOption(topOption);
            }
        });
    };

    // body绑定事件，触发loadData函数
    $("body").bind("clientReload", function() {
        renderBlueCode();
    });
})();
// 蓝屏终端页-蓝屏地区TOP5（每万终端）
(function() {
    // 获取图表所在div
    var oDiv = document.getElementById('blue_top5');
    // 近12小时

    // 指定图标的配置项
    var blueTopOption = {
        // 图例组件配置
        legend: {
            data: [{
                name: "万终端蓝屏数",
                icon: 'roundRect'
            }]
        },
        // x轴配置
        xAxis: [{
            data: []
        }],
        // y轴配置
        yAxis: [{
            interval: 0.1
        }],
        // 系列列表，曲线区域图
        series: [{
            name: '万终端蓝屏数',
            type: 'bar',
            barWidth: 20,
            markLine: {
                data: [{
                    name: '平均值',
                    type: 'average'
                }],
                symbol: 'none',
                label: {
                    normal: {
                        show: false
                    }
                },
                lineStyle: {
                    normal: {
                        color: '#9d6bc0',
                        width: 2,
                        type: 'solid',
                        shadowColor: '#9d6bc0',
                        shadowBlur: 10,
                        shadowOffsetY: 2
                    }
                }
            },
            data: []
        }]
    }

    //初始化图表
    var chart = null;
    var renderBlueScreenRegion = function() {
        loadData("region_blue_bar_num.json", function(res) {
            if (chart) {
                // 表格已有，刷新表格
                refreshChartxAxis(chart, res.data[0]);
                onlyRefreshChartData(chart, res.data[1]);
            } else {
                // 表格不存在，创建表格
                blueTopOption.xAxis[0].data = res.data[0];
                blueTopOption.series[0].data = res.data[1];
                chart = renderBarChart(blueTopOption, oDiv);
            }
        });

    };
    // body绑定事件，触发loadData函数
    $("body").bind("clientReload", function() {
        renderBlueScreenRegion();
    });
})();
// 蓝屏终端页-近12小时蓝屏终端数地图
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
            name: '蓝屏终端数',
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
                    color: '#00e6de',
                    shadowBlur: 10,
                    shadowColor: '#333',
                    opacity: 1
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
                chart = renderMap("china", "blue_pc_map", { width: 900, height: 850 }, chinaOpts);
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
/**
 * @requires echarts-bluebar.js
 * @requires echarts-bluepc.js
 * @requires echarts-bluetop10.js
 * @requires echarts-bluetop5.js
 * @requires echarts-map.js
 **/