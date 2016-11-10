import re;
def parse(html):
	itempattern = re.compile(r'<li class="subject-item">.*?</li>',re.DOTALL|re.I)
	res=itempattern.findall(html)
	namelist=''
	for item in res:
		item_info = ''
		pattern_temp = re.compile(r'<h2 class="">\s*?<a href="https://book.douban.com/subject.*?>\s*(.*?)\s*</a>',re.DOTALL|re.I)
		name_before = pattern_temp.findall(item)[0]
		patternname = re.compile(r'\S+',re.DOTALL)
		name = patternname.search(name_before).group(0)
		
		if(name_before.find(r'<span style')>-1):
			begin = name_before.find(r'">') + 2
			end = name_before.find(r'</span>')
			if(begin<end):
				subname=name_before[begin:end]
				name+=subname
		item_info += name
		pattern_pl = re.compile(r'<span class="pl">\s*(.*?)\s*</span>',re.DOTALL|re.I)
		pl = pattern_pl.findall(item)[0]
		item_info+=pl
		pattern_goal = re.compile(r'<span class="rating_nums">\s*(.*?)\s*</span>',re.DOTALL|re.I)
		goal = pattern_goal.findall(item)[0]
		item_info+=goal
		namelist+=item_info
		namelist+='_;_'
	return namelist


