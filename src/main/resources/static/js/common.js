function setToken(token) {
	document.cookie = 'token=' + token
}

function getToken() {
	if (document.cookie.length > 0) {
		var cstart = document.cookie.indexOf('token=')
		if (cstart !== -1) {
			cstart = cstart + 'token='.length
			var cend = document.cookie.indexOf(';', cstart)
			if (cend === -1)
				cend = document.cookie.length
			return unescape(document.cookie.substring(cstart, cend))
		}
	}
	return null
}