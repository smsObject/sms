/**
 * Created by Tomtao on 2018/1/3.
 */
/**
 * 将日期格式化成指定格式的字符串
 * @param date 要格式化的日期，不传时默认当前时间，也可以是一个时间戳（秒或毫秒值）
 * @param fmt 目标字符串格式，支持的字符有：y,M,d,q,w,H,h,m,S，默认：yyyy-MM-dd HH:mm:ss
 * @returns 返回格式化后的日期字符串
 */
function formatDate(date, fmt) {
    //date为null时使用当前时间
    date = date == undefined ? new Date() : date;
    //兼容传输时间戳（秒或毫秒）
    if (typeof date == 'number') {
        //10位数字时识别为秒，自动转换为毫秒
        if ((date + "").length == 10) {
            date = date * 1000;
        }
        date = new Date(date);
    }
    fmt = fmt || 'yyyy-MM-dd HH:mm:ss';
    var obj =
    {
        'y': date.getFullYear(), // 年份，注意必须用getFullYear
        'M': date.getMonth() + 1, // 月份，注意是从0-11
        'd': date.getDate(), // 日期
        'q': Math.floor((date.getMonth() + 3) / 3), // 季度
        'w': date.getDay(), // 星期，注意是0-6
        'H': date.getHours(), // 24小时制
        'h': date.getHours() % 12 == 0 ? 12 : date.getHours() % 12, // 12小时制
        'm': date.getMinutes(), // 分钟
        's': date.getSeconds(), // 秒
        'S': date.getMilliseconds() // 毫秒
    };
    var week = ['天', '一', '二', '三', '四', '五', '六'];
    for (var i in obj) {
        fmt = fmt.replace(new RegExp(i + '+', 'g'), function (m) {
            var val = obj[i] + '';
            if (i == 'w') return (m.length > 2 ? '星期' : '周') + week[val];
            for (var j = 0, len = val.length; j < m.length - len; j++) val = '0' + val;
            return m.length == 1 ? val : val.substring(val.length - m.length);
        });
    }
    return fmt;
}

/**
 * 将日期字符串转换为Javascript Date对象
 * @param dateStr 日期字符串
 * @returns {Date} Javascript Date对象
 */
function parseDate(dateStr) {
    if (!dateStr) {
        return new Date();
    }
    return new Date(Date.parse(dateStr.replace(/-/g, "/")));
}

function formatDateToYearMonth(date) {
    return formatDate(date, "yyyy-MM")
}

function formatDateToYearMonthEn(date) {
    return formatDate(date, "MM/yyyy")
}

function formatDateToYearMonthDay(date) {
    return formatDate(date, "yyyy-MM-dd")
}

function formatDateToYearMonthDayEn(date) {
    return formatDate(date, "MM/dd/yyyy")
}

function formatDateToYearMonthDayHourMinuteSecond(date) {
    return formatDate(date, "yyyy-MM-dd HH:mm:ss")
}

function formatDateToYearMonthDayHourMinuteSecondEn(date) {
    return formatDate(date, "MM/dd/yyyy HH:mm:ss")
}

function getFirstDayDateWithAddMonth(addMonth) {
    var date = new Date();
    date.setMonth(date.getMonth() + addMonth);
    date.setDate(1);
    date.setHours(0);
    date.setMinutes(0);
    date.setSeconds(0);
    date.setMilliseconds(0);
    return date;
}