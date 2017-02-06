// 崩溃终端页-崩溃网吧尾号分布情况
(function() {
    // 获取图表所在div
    var oDiv = document.getElementById('crash_bar_code');
    // 近12小时
    var xaxis = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9];
    // 指定图标的配置项
    var crashBarOption = {
        // 图例组件配置
        color: ['#ff9000'],
        legend: {
            data: [{
                name: "崩溃网吧数",
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
            name: '崩溃网吧数',
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
                        color: '#187efe',
                        width: 2,
                        type: 'solid',
                        shadowColor: '#187efe',
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
    var renderCrashBarTailNumber = function() {
        loadData('bar_tail_num.json', function(res) {
            // 判断图表是否存在，如果不存在渲染一个，如果已经存在刷新数据
            if (chart) {
                onlyRefreshChartData(chart, res.data);
            } else {
                crashBarOption.series[0].data = res.data;
                chart = renderBarChart(crashBarOption, oDiv);
            }
        })

    };

    // body绑定事件，触发renderCrashBarTailNumber函数
    $("body").bind("clientReload", function() {
        renderCrashBarTailNumber();
    });
})();
// 崩溃终端页-崩溃终端尾号分布情况
(function() {
    // 获取图表所在div
    var oDiv = document.getElementById('crash_pc_code');
    // x轴
    var xaxis = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9];
    // 指定图标的配置项
    var crashPcOption = {
        // 图例组件配置
        color: ['#ff9000'],
        legend: {
            data: [{
                name: "崩溃终端数",
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
            name: '崩溃终端数',
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
                        color: '#187efe',
                        width: 2,
                        type: 'solid',
                        shadowColor: '#187efe',
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
    var renderCrashClientTailNumber = function() {
        loadData('bar_tail_num.json', function(res) {
            // 判断图表是否存在，如果不存在渲染一个，如果已经存在刷新数据
            if (chart) {
                onlyRefreshChartData(chart, res.data);
            } else {
                crashPcOption.series[0].data = res.data;
                chart = renderBarChart(crashPcOption, oDiv);
            }
        })

    }

    //renderCrashClientTailNumber();
    // body绑定事件，触发loadData函数
    $("body").bind("clientReload", function() {
        renderCrashClientTailNumber();
    });
})();
// 崩溃终端页-近12小时崩溃地区TOP5（每万终端）
(function() {
    // 获取图表所在div
    var oDiv = document.getElementById('crash_top5');
    // 近12小时
    var data = [
            ['菏泽', '合肥', '武汉', '南昌', '大庆'],
            [0.38, 0.35, 0.25, 0.18, 0.12]
        ]
        // 指定图标的配置项
    var crashTopOption = {
        // 图例组件配置
        color: ['#ff9000'],
        legend: {
            data: [{
                name: "万终端崩溃数",
                icon: 'roundRect'
            }]
        },
        // x轴配置
        xAxis: [{
            data: data[0]
        }],
        // y轴配置
        yAxis: [{
            interval: 0.1
        }],
        // 系列列表，曲线区域图
        series: [{
            name: '万终端崩溃数',
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
                        color: '#187efe',
                        width: 2,
                        type: 'solid',
                        shadowColor: '#187efe',
                        shadowBlur: 10,
                        shadowOffsetY: 2
                    }
                }
            },
            data: data[1]
        }]
    }

    //初始化图表
    var chart = null;
    var renderCrashRegion = function() {
        loadData("region_blue_bar_num.json", function(res) {
            if (chart) {
                // 表格已有，刷新表格
                refreshChartxAxis(chart, res.data[0]);
                onlyRefreshChartData(chart, res.data[1]);
            } else {
                // 表格不存在，创建表格
                crashTopOption.xAxis[0].data = res.data[0];
                crashTopOption.series[0].data = res.data[1];
                chart = renderBarChart(crashTopOption, oDiv);
            }
        });

    };
    // body绑定事件，触发loadData函数
    $("body").bind("clientReload", function() {
        renderCrashRegion();
    });
})();
// 崩溃终端页-客户端崩溃代码TOP10
(function() {
    // 获取图表所在div
    var top = echarts.init(document.getElementById('crash_top10'));

    // 指定图标的配置项
    var crashTopOption = {
        tooltip: {},
        series: [{
            type: 'wordCloud',
            gridSize: 20,
            sizeRange: [12, 30],
            rotationRange: [0, 0],
            shape: 'rect',
            textPadding: 0,
            autoSize: {
                enable: true,
                minSize: 14
            },
            textStyle: {
                normal: {
                    color: function() {
                        return 'rgb(' + [
                            90 + Math.round(Math.random() * 160),
                            90 + Math.round(Math.random() * 160),
                            90 + Math.round(Math.random() * 160)
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
    var renderCrashCode = function() {
        //加载数据
        loadData('blue_screen_code.json', function(res) {
            if (chart)
                onlyRefreshChartData(chart, res.data)
            else {
                crashTopOption.series[0].data = res.data;
                chart = top.setOption(crashTopOption);
            }
        });
    };

    // body绑定事件，触发loadData函数
    $("body").bind("clientReload", function() {
        renderCrashCode();
    });
})();
// 崩溃终端页-近12小时崩溃终端地图
(function() {
    var chinaData = [{
        name: '重庆',
        value: 11500
    }, {
        name: '云南',
        value: 22500
    }, {
        name: '辽宁',
        value: 331500
    }, {
        name: '黑龙江',
        value: 44500
    }, {
        name: '广西',
        value: 55500
    }, {
        name: '山西',
        value: 66200
    }, {
        name: '陕西',
        value: 123500
    }, {
        name: '吉林',
        value: 235500
    }, {
        name: '贵州',
        value: 11500
    }, {
        name: '新疆',
        value: 222500
    }, {
        name: '青海',
        value: 333500
    }, {
        name: '西藏',
        value: 33500
    }, {
        name: '四川',
        value: 5000
    }, {
        name: '宁夏',
        value: 500
    }, {
        name: '海南',
        value: 500
    }, {
        name: '台湾',
        value: 500
    }, {
        name: '香港',
        value: 500
    }, {
        name: '上海',
        value: 18000
    }, {
        name: '安徽',
        value: 5000
    }, {
        name: '江苏',
        value: 15000
    }, {
        name: '浙江',
        value: 120000
    }, {
        name: '北京',
        value: 1000
    }, {
        name: '天津',
        value: 10000
    }, {
        name: '河北',
        value: 10000
    }, {
        name: '河南',
        value: 1000
    }, {
        name: '内蒙古',
        value: 1000
    }, {
        name: '湖南',
        value: 1000
    }, {
        name: '山东',
        value: 1000
    }, {
        name: '江西',
        value: 1000
    }, {
        name: '湖北',
        value: 1000
    }, {
        name: '福建',
        value: 1000
    }, {
        name: '广东',
        value: 1000
    }];

    var ITRooms = [{
            "name": "北京",
            "value": 0.2
        },
        {
            "name": "甘肃",
            "value": 1.7
        },
        {
            "name": "广东",
            "value": 6.01
        },
        {
            "name": "河北",
            "value": 1.5
        },
        {
            "name": "湖北",
            "value": 5.7
        },
        {
            "name": "湖南",
            "value": 5
        },
        {
            "name": "吉林",
            "value": 3
        },
        {
            "name": "江苏",
            "value": 5.51
        },
        {
            "name": "辽宁",
            "value": 2
        },
        {
            "name": "山东",
            "value": 1.5
        },
        {
            "name": "山西",
            "value": 2.51
        },
        {
            "name": "四川",
            "value": 2.11
        },
        {
            "name": "香港",
            "value": 0.01
        },
        {
            "name": "浙江",
            "value": 11.75
        }
    ];

    var geoCoordMap = {
        "台湾": [121.509062, 25.044332],
        "河北": [114.502461, 38.045474],
        "山西": [112.549248, 37.857014],
        "内蒙古": [111.670801, 40.818311],
        "辽宁": [123.429096, 41.796767],
        "吉林": [125.3245, 43.886841],
        "黑龙江": [126.642464, 45.756967],
        "江苏": [118.767413, 32.041544],
        "浙江": [120.153576, 30.287459],
        "安徽": [117.283042, 31.86119],
        "福建": [119.306239, 26.075302],
        "江西": [115.892151, 28.676493],
        "山东": [117.000923, 36.675807],
        "河南": [113.665412, 34.757975],
        "湖北": [114.298572, 30.584355],
        "湖南": [112.982279, 28.19409],
        "广东": [113.280637, 23.125178],
        "广西": [108.320004, 22.82402],
        "海南": [110.33119, 20.031971],
        "四川": [104.065735, 30.659462],
        "贵州": [106.713478, 26.578343],
        "云南": [102.712251, 25.040609],
        "西藏": [91.132212, 29.660361],
        "陕西": [108.948024, 34.263161],
        "甘肃": [103.823557, 36.058039],
        "青海": [101.778916, 36.623178],
        "宁夏": [106.278179, 38.46637],
        "新疆": [87.617733, 43.792818],
        "北京": [116.405285, 39.904989],
        "天津": [117.190182, 39.125596],
        "上海": [121.472644, 31.231706],
        "重庆": [106.504962, 29.533155],
        "香港": [114.173355, 22.320048]
    };

    function convertData(data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
            var geoCoord = geoCoordMap[data[i].name];
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
            data: convertData(ITRooms),
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
                    color: '#ff9000',
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
                chart = renderMap("china", "crash_pc_map", { width: 900, height: 850 }, chinaOpts);
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
 * @requires echarts-crashbar.js
 * @requires echarts-crashpc.js
 * @requires echarts-crashtop5.js
 * @requires echarts-crashtop10.js
 * @requires echarts-map.js
 **/