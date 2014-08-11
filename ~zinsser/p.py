#!/usr/bin/python
import re
import urllib2
url=r"http://calendar.ucsd.edu/"
page=urllib2.urlopen(url)
source=page.read()
regex=r"<span class =\"verdan-12px-ylw-content\"><strong>(.*?)</strong></span><br>[\n\s]*<div class=\"horizontal_dotted_line_long\">[\n\s]*<p class=\"verdana-11px-proom\">[\n\s]*<table width=\"430\" border=\"0\">[\n\s]*<tr valign=\"top\">[\n\s]*<td><img class=resizeme id=\"IMG1\" border=\"0\" width=\"138\"  src=\"(.*?)\" align=\"top\" style=\"margin-right:5px;\" ></td>[\n\s]*<td><p class=\"verdana-11px-proom\">[\n\s]*<strong>Date: </strong>(.*?)[\n\s\r ]*?                  (.*?)[\n\s]*<br>[\n\s]*<strong>Location: </strong>(.*?)<br><br>[\n\s]*<strong>(.*?)</strong>&nbsp;&nbsp;[\n\s]*<a target=\"_parent\" href=\"DisplayEventDetail\.asp\?iEventID=(.*?)&.*?\">more</a>[\n\s]*</p>[\n\s]*</td>[\n\s]*</tr>[\n\s]*</table>[\n\s]*</p></div>"
matchlist=re.finditer(regex,source,0)
f=open('output','w')
for ob in matchlist:
    for n in range(1,8):
        f.write(str(n)+' '+ob.group(n)+'\n')
    f.write ("\n\n========================================\n")


{"postDate": "2014-03-01 20:13:50.214900", "resource": null, "title": "3/1/14 Event", "text": " This is a test description", "image": "http://static3.businessinsider.com/image/52a0bbfd6bb3f7961363819e/the-most-amazing-satellite-images-of-the-year.jpg", "tag": null, "activityDate": "2014-04-26", "intro": "Test", "id": 5685265389584384}

