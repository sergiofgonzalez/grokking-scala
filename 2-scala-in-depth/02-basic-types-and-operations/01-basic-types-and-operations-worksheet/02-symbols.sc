def updateRecordByName(r: Symbol, value: Any) = {
  // the update code goes here
}

// updateRecordByName(favoriteAlbum, "OK Computer") // Err: not found: value favoriteAlbum

updateRecordByName('favoriteAlbum, "OK Computer")

val s = 'aSymbol
val symbolName = s.name