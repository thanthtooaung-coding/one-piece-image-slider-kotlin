package com.vinn.imagesliderapplication

class ImageRepository {
    fun getImages(): List<ImageItem> {
        return listOf(
            ImageItem(R.drawable.luffy, "Monkey D. Luffy", "Captain of the Straw Hat Pirates who dreams of becoming the Pirate King."),
            ImageItem(R.drawable.zoro, "Roronoa Zoro", "The crew's combatant, a master of the Three Sword Style aiming to be the world's greatest swordsman."),
            ImageItem(R.drawable.sanji, "Vinsmoke Sanji", "The crew's chef, a master of the Black Leg Style, who dreams of finding the All Blue."),
            ImageItem(R.drawable.nami, "Nami", "The crew's brilliant navigator, who has a dream of drawing a map of the entire world."),
            ImageItem(R.drawable.usopp, "Usopp", "The crew's sniper and a brave warrior of the sea with a knack for incredible stories.")
        )
    }
}
