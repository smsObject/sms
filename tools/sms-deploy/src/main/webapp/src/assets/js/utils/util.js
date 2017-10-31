/**
 * Created by Administrator on 2017/5/21 0021.
 */
const chnNumChar = ["零","一","二","三","四","五","六","七","八","九"]
const chnUnitSection = ["","万","亿","万亿","亿亿"]
const chnUnitChar = ["","十","百","千"]
const SectionToChinese = (section) => {
	var strIns = '', chnStr = '';
	var unitPos = 0;
	var zero = true;
	while(section > 0){
		var v = section % 10;
		if(v === 0){
			if(!zero){
				zero = true;
				chnStr = chnNumChar[v] + chnStr;
			}
		}else{
			zero = false;
			strIns = chnNumChar[v];
			strIns += chnUnitChar[unitPos];
			chnStr = strIns + chnStr;
		}
		unitPos++;
		section = Math.floor(section / 10);
	}
	return chnStr
}
export const numberToChinese = (num) => {
	let unitPos = 0;
	let strIns = '', chnStr = '';
	let needZero = false;

	if(num === 0){
		return chnNumChar[0];
	}

	while(num > 0){
		var section = num % 10000;
		if(needZero){
			chnStr = chnNumChar[0] + chnStr;
		}
		strIns = SectionToChinese(section);
		strIns += (section !== 0) ? chnUnitSection[unitPos] : chnUnitSection[0];
		chnStr = strIns + chnStr;
		needZero = (section < 1000) && (section > 0);
		num = Math.floor(num / 10000);
		unitPos++;
	}
    return chnStr
}

export const deepCopy = function(o) {
	if (o instanceof Array) {
		var n = [];
		for (var i = 0; i < o.length; ++i) {
			n[i] = deepCopy(o[i]);
		}
		return n;

	} else if (o instanceof Object) {
		var n = {}
		for (var i in o) {
			n[i] = deepCopy(o[i]);
		}
		return n;
	} else {
		return o;
	}
}

function hasZero(o) {
	return o < 10 ? "0"+o : o
}
export const formatDate = (now) => {
	if(typeof now === "string" || !now) return now
	now = new Date(now)
	var   year=now.getYear()+1900;
	var   month=now.getMonth()+1;
	var   date=now.getDate();
	return   hasZero(year)+"-"+hasZero(month)+"-"+hasZero(date)
}