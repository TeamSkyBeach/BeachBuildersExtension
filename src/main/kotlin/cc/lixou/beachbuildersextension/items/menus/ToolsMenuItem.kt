package cc.lixou.beachbuildersextension.items.menus

import cc.lixou.stracciatella.inventory.extensions.styleRadialBackground
import cc.lixou.stracciatella.item.CustomItem
import cc.lixou.stracciatella.item.extensions.getCreamID
import net.minestom.server.entity.Player
import net.minestom.server.event.inventory.InventoryPreClickEvent
import net.minestom.server.event.player.PlayerSwapItemEvent
import net.minestom.server.event.player.PlayerUseItemEvent
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType
import net.minestom.server.item.Material
import world.cepi.kstom.adventure.formatMini
import world.cepi.kstom.adventure.noItalic

object ToolsMenuItem : CustomItem(
    "beachbuilders.toolsmenuitem",
    Material.IRON_AXE,
    {
        it.meta { meta ->
            meta.displayName(
                "<gray>» <gradient:#cecece:white><bold>Tools</bold> <gray>«".formatMini().noItalic()
            )
        }
        it.event(PlayerUseItemEvent::class.java) { event ->
            if (!ToolsMenuItem.validate(event.itemStack)) return@event
            ToolsMenuItem.openInventory(event.player)
            event.isCancelled = true
        }
        it.event(InventoryPreClickEvent::class.java) { event ->
            if (!ToolsMenuItem.validate(event.clickedItem, event.cursorItem)) {
                return@event
            }
            ToolsMenuItem.openInventory(event.player)
            event.isCancelled = true
        }
        it.event(PlayerSwapItemEvent::class.java) { event ->
            if (!ToolsMenuItem.validate(event.mainHandItem, event.offHandItem)) return@event
            ToolsMenuItem.openInventory(event.player)
            event.isCancelled = true
        }
    }
) {

    private fun openInventory(player: Player) {
        val inventory =
            Inventory(InventoryType.CHEST_3_ROW, "<gradient:#cecece:white><bold>Tools".formatMini())
        inventory.styleRadialBackground(Material.LIGHT_GRAY_STAINED_GLASS_PANE, 3)
        player.openInventory(inventory)
    }

}