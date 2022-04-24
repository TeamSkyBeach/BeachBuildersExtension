package cc.lixou.beachbuildersextension.items

import cc.lixou.beachbuildersextension.items.menus.ToolsMenuItem
import cc.lixou.stracciatella.inventory.extensions.setItemStack
import cc.lixou.stracciatella.inventory.extensions.styleRadialBackground
import cc.lixou.stracciatella.item.CustomItem
import cc.lixou.stracciatella.item.extensions.headTexture
import net.minestom.server.entity.Player
import net.minestom.server.event.inventory.InventoryPreClickEvent
import net.minestom.server.event.player.PlayerBlockPlaceEvent
import net.minestom.server.event.player.PlayerSwapItemEvent
import net.minestom.server.event.player.PlayerUseItemEvent
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType
import net.minestom.server.item.Material
import net.minestom.server.item.metadata.PlayerHeadMeta
import world.cepi.kstom.adventure.formatMini
import world.cepi.kstom.adventure.noItalic

object MenuItem : CustomItem(
    "beachbuilders.menuitem",
    Material.PLAYER_HEAD,
    {
        it.meta(PlayerHeadMeta::class.java) { meta ->
            meta.headTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzUzNWZmZDY5N2Q2YzBkOTc2MTc1ODE2ZTQxNjhjYmM0Y2EyOWQzYThlNzFiMTNhMWQ1Zjk1NDNjNTYyY2ExIn19fQ==")
            meta.displayName(
                "<gray>» <gradient:gold:yellow><bold>BeachBuilders Menu</bold> <gray>«".formatMini().noItalic()
            )
        }
        it.event(PlayerUseItemEvent::class.java) { event ->
            if (!MenuItem.validate(event.itemStack)) return@event
            MenuItem.openInventory(event.player)
            event.isCancelled = true
        }
        it.event(InventoryPreClickEvent::class.java) { event ->
            if (!MenuItem.validate(event.clickedItem, event.cursorItem)) return@event
            MenuItem.openInventory(event.player)
            event.isCancelled = true
        }
        it.event(PlayerSwapItemEvent::class.java) { event ->
            if (!MenuItem.validate(event.mainHandItem, event.offHandItem)) return@event
            MenuItem.openInventory(event.player)
            event.isCancelled = true
        }
        it.event(PlayerBlockPlaceEvent::class.java) { event ->
            if (!MenuItem.validate(event.player.inventory.getItemInHand(event.hand))) return@event
            MenuItem.openInventory(event.player)
            event.isCancelled = true
        }
    }
) {

    private fun openInventory(player: Player) {
        val inventory =
            Inventory(InventoryType.CHEST_5_ROW, "<gradient:gold:yellow><bold>BeachBuilders Menu".formatMini())
        inventory.styleRadialBackground(Material.ORANGE_STAINED_GLASS_PANE, 5)
        inventory.setItemStack(3, 4, ToolsMenuItem.createItemStack())
        player.openInventory(inventory)
    }

}