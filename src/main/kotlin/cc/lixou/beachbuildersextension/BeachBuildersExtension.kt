package cc.lixou.beachbuildersextension

import cc.lixou.beachbuildersextension.items.MenuItem
import net.minestom.server.event.player.PlayerSpawnEvent
import net.minestom.server.extensions.Extension

class BeachBuildersExtension : Extension() {

    override fun initialize() {
        eventNode.addListener(PlayerSpawnEvent::class.java) { event ->
            val player = event.player
            player.inventory.setItemStack(8, MenuItem.createItemStack())
        }
        println("[BeachBuilders] Initialized successfully")
    }

    override fun terminate() {
        println("[BeachBuilders] Terminated successfully")
    }

}