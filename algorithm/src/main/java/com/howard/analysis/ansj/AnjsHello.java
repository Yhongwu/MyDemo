package com.howard.analysis.ansj;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.BaseAnalysis;
import org.ansj.splitWord.analysis.IndexAnalysis;
import org.ansj.splitWord.analysis.ToAnalysis;

/**
 * ansj_seq
 * 需要jar包：nlp-lang.jar和ansj_seg
 * maven项目直接依赖ansj_seg即可
 * ansj_seq是国内开源项目：
 * 源码：https://github.com/NLPchina/ansj_seg
 * 文档：http://nlpchina.github.io/ansj_seg/
 * @author hongwu
 *
 */
public class AnjsHello {
	/**
	 * 基本分词BaseAnalysis
	 * 保证了最基本的分词.词语颗粒度最非常小的..所涉及到的词大约是10万左右.
	 * 功能：支持：数字识别 不支持：用户自定义词典 人名识别  机构名识别 新词发现
	 * 特点：速度非常快 每秒300w字每秒.同时准确率也很高.但是对于新词他的功能十分有限
	 * @param src
	 */
	private static void baseAnalysis(String src) {
		Result parse = BaseAnalysis.parse(src);
		List<Term> terms = parse.getTerms();
	    System.out.println(terms);
	    //String word = terms.get(0).getName();// 获取单词  
        //String nominal = terms.get(0).getNatureStr();// 获取词性  
		
	}
	/**
	 * 精准分词ToAnalysis
	 * 易用性,稳定性.准确性.分词效率都相对平衡
	 * 功能：支持：用户自定义词典 数字识别 人名识别 不支持：机构名识别 新词发现
	 * @param src
	 */
	private static void toAnalysis(String src) {
		Result parse = ToAnalysis.parse(src);
		List<Term> terms = parse.getTerms();
	    System.out.println(terms);
	}
	
	/**
	 * nlp分词NlpAnalysis
	 * 功能：支持：用户自定义词典 数字识别 人名识别 机构名识别 新词发现
	 * 特点：可识别出未登录词.速度比较慢.稳定性差
	 * @param src
	 */
	private static void nlpAnalysis(String src) {
		Result parse = ToAnalysis.parse(src);
		List<Term> terms = parse.getTerms();
	    System.out.println(terms);
	}
	/**
	 * 面向索引分词
	 * 主要配合lucene使用 
	 * 支持：用户自定义词典 数字识别 人名识别  不支持： 机构名识别 新词发现
	 * 测试未明确
	 * @param src
	 */
	private static void indexAnalysis(String src) {
		Result parse = IndexAnalysis.parse(src);
		List<Term> terms = parse.getTerms();
	    System.out.println(terms);
	}
	/**
	 * 过滤词性演示
	 * @param src
	 * @return
	 */
	private static void parseWord(String src) {
		List<String> keywords = new ArrayList<>();
		//只保留这部分词性
		Set<String> expectedNature = new HashSet<String>() {{
	        add("n");add("vd");add("vn");add("j");
	    }};
	    Result parse = ToAnalysis.parse(src);
		List<Term> term = parse.getTerms();
		for (int i = 0; i < term.size(); i++) {  
	        String word = term.get(i).getName();// 获取单词  
	        String nominal = term.get(i).getNatureStr();// 获取词性  
	    	if(expectedNature.contains(nominal)) {
//	    		keywords.add(word);
	    		System.out.print(word+"/"+nominal+" ");
	        }
	    } 
		System.out.println();
	}
	
	
	public static void main(String[] args) {
		String src = "人名：张无忌,10kV～500kV输变电及配电工程质量验收与评定标准（变电土建工程上）册";
		baseAnalysis(src);
		toAnalysis(src);
		nlpAnalysis(src);
		indexAnalysis(src);
		parseWord(src);
	}
	
}
/**
词性说明：
# 1. 名词  (1个一类，7个二类，5个三类)
名词分为以下子类：
n 名词
nr 人名
nr1 汉语姓氏
nr2 汉语名字
nrj 日语人名
nrf 音译人名
ns 地名
nsf 音译地名
nt 机构团体名
nz 其它专名
nl 名词性惯用语
ng 名词性语素
nw 新词
# 2. 时间词(1个一类，1个二类)
t 时间词
tg 时间词性语素
# 3. 处所词(1个一类)
s 处所词
# 4. 方位词(1个一类)
f 方位词
# 5. 动词(1个一类，9个二类)
v 动词
vd 副动词
vn 名动词
vshi 动词“是”
vyou 动词“有”
vf 趋向动词
vx 形式动词
vi 不及物动词（内动词）
vl 动词性惯用语
vg 动词性语素
# 6. 形容词(1个一类，4个二类)
a 形容词
ad 副形词
an 名形词
ag 形容词性语素
al 形容词性惯用语
# 7. 区别词(1个一类，2个二类)
b 区别词
bl 区别词性惯用语
# 8. 状态词(1个一类)
z 状态词
# 9. 代词(1个一类，4个二类，6个三类)
r 代词
rr 人称代词
rz 指示代词
rzt 时间指示代词
rzs 处所指示代词
rzv 谓词性指示代词
ry 疑问代词
ryt 时间疑问代词
rys 处所疑问代词
ryv 谓词性疑问代词
rg 代词性语素
# 10. 数词(1个一类，1个二类)
m 数词
mq 数量词
# 11. 量词(1个一类，2个二类)
q 量词
qv 动量词
qt 时量词
# 12. 副词(1个一类)
d 副词
# 13. 介词(1个一类，2个二类)
p 介词
pba 介词“把”
pbei 介词“被”
# 14. 连词(1个一类，1个二类)
c 连词
 cc 并列连词
# 15. 助词(1个一类，15个二类)
u 助词
uzhe 着
ule 了 喽
uguo 过
ude1 的 底
ude2 地
ude3 得
usuo 所
udeng 等 等等 云云
uyy 一样 一般 似的 般
udh 的话
uls 来讲 来说 而言 说来
uzhi 之
ulian 连 （“连小学生都会”）
# 16. 叹词(1个一类)
e 叹词
# 17. 语气词(1个一类)
y 语气词(delete yg)
# 18. 拟声词(1个一类)
o 拟声词
# 19. 前缀(1个一类)
h 前缀
# 20. 后缀(1个一类)
k 后缀
# 21. 字符串(1个一类，2个二类)
x 字符串
 xx 非语素字
 xu 网址URL
# 22. 标点符号(1个一类，16个二类)
w 标点符号
wkz 左括号，全角：（ 〔  ［  ｛  《 【  〖〈   半角：( [ { <
wky 右括号，全角：） 〕  ］ ｝ 》  】 〗 〉 半角： ) ] { >
wyz 左引号，全角：“ ‘ 『 
wyy 右引号，全角：” ’ 』
wj 句号，全角：。
ww 问号，全角：？ 半角：?
wt 叹号，全角：！ 半角：!
wd 逗号，全角：， 半角：,
wf 分号，全角：； 半角： ;
wn 顿号，全角：、
wm 冒号，全角：： 半角： :
ws 省略号，全角：……  …
wp 破折号，全角：——   －－   ——－   半角：---  ----
wb 百分号千分号，全角：％ ‰   半角：%
wh 单位符号，全角：￥ ＄ ￡  °  ℃  半角：$

*/