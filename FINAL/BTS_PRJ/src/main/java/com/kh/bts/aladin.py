# -*- coding:utf-8 -*-
from bs4 import BeautifulSoup
import urllib.request
import json
import sys
from urllib.parse import quote
from pymongo import MongoClient

bookname = str(sys.argv[1])
print(bookname)
client = MongoClient('localhost',27017)
db = client.test_database
collection = db.bookstore
collection2 = db.bookstoreimgsrc

a = bookname;
str_euckr = quote(a.encode('euc-kr'))
print(str_euckr)
resp = urllib.request.urlopen("https://www.aladin.co.kr/search/wsearchresult.aspx?SearchTarget=All&SearchWord=%s" % str_euckr)
soup = BeautifulSoup(resp, 'html.parser')
booklist = soup.find('div', {'id':'Search3_Result'})
div = booklist.select('b')

div2 = booklist.select('img', {'class':'i_cover'})
cnt2 = 0
tmp2 = {}
lst2 = list()
for chd in div2:
    if 'http' in chd.get('src') :
        tmp2['imgsrc'] = chd.get('src')
        lst2.append(tmp2)
        tmp2 = {}
        
        
res = {}
lst = list()
num = 0
cnt = 0
cnt2 = 0
tmp = {}
for chd in div:
    if cnt % 3 == 0 :
        num = num + 1  
        tmp['rank'] = num  
        tmp['title'] = chd.text
    elif cnt % 3 == 1 :
        tmp['value'] = chd.text
    else :
        if cnt2 < 10 :
            tmp['src'] = lst2[(int)((cnt+1)/3)].get('imgsrc')
            lst.append(tmp)
            tmp = {}
            cnt2 = cnt2+1
    cnt = cnt + 1
collection.insert_many(lst)
collection2.insert_many(lst2)
'''
res['booklist'] = lst
res_json = json.dumps(res['booklist'], ensure_ascii=False)
with open("aladin.json", "w", encoding='utf-8')as f:
    f.write(res_json)
'''

'''
docs = collection.find()
for i in docs:
    print(i)
'''