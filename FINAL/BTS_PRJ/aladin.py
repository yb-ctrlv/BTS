# -*- coding:utf-8 -*-
from bs4 import BeautifulSoup
import urllib.request
import json
from urllib.parse import quote
from pymongo import MongoClient

client = MongoClient('localhost',27017)
db = client.test_database
collection = db.emp4

a = '정보처리기사'
str_euckr = quote(a.encode('euc-kr'))
print(str_euckr)
resp = urllib.request.urlopen("https://www.aladin.co.kr/search/wsearchresult.aspx?SearchTarget=All&SearchWord=%s" % str_euckr)
soup = BeautifulSoup(resp, 'html.parser')
booklist = soup.find('div', {'id':'Search3_Result'})
div = booklist.select('b')
res = {}
lst = list()
num = 0
cnt = 0
tmp = {}
for chd in div:
    if cnt % 3 == 0 :
        num = num + 1  
        tmp['_id'] = num  
        tmp['title'] = chd.text
    elif cnt % 3 == 1 :
        tmp['value'] = chd.text
    else :
        lst.append(tmp)
        tmp = {}
    cnt = cnt + 1
    
collection.insert_many(lst)
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