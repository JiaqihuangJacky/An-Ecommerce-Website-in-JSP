def increm(val,mp,aph):
	base = len(aph)
	# reverse string
	rev = val[::-1]
	res = ''
	res += aph[ int( (mp[rev[0]]+1)%base )]
	arc = int((mp[rev[0]]+1) / base)
	for i in range(1,len(rev)-1):
		s = rev[i]
		temp = mp[s]
		res += aph[int((temp+arc)%base)]
		arc = int((arc+temp)/base)
	return res[::-1]


aph = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ';
mp = {}

for i in range(0,len(aph)):
	mp[aph[i]] = i
print(mp)

print(increm('0000000000',mp,aph))
