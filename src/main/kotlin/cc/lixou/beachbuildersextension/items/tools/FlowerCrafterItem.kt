package cc.lixou.beachbuildersextension.items.tools

import cc.lixou.stracciatella.inventory.extensions.styleRadialBackground
import cc.lixou.stracciatella.item.CustomItem
import net.minestom.server.entity.Player
import net.minestom.server.event.inventory.InventoryPreClickEvent
import net.minestom.server.event.player.PlayerHandAnimationEvent
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType
import net.minestom.server.item.Material
import world.cepi.kstom.adventure.formatMini
import world.cepi.kstom.adventure.noItalic

object FlowerCrafterItem : CustomItem(
    "beachbuilders.tools.flowercrafter",
    Material.FLOWER_POT,
    {
        it.meta { meta ->
            meta.displayName(
                "<gray>» <gradient:#672f0a:#944300><bold>FlowerCrafter</bold> <gray>«".formatMini().noItalic()
            )
        }
        it.event(InventoryPreClickEvent::class.java) { event ->
            if (!FlowerCrafterItem.validate(event.clickedItem)) return@event
        }
        it.event(PlayerHandAnimationEvent::class.java) { event ->
            if (!FlowerCrafterItem.validate(event.player.getItemInHand(event.hand))) return@event
            FlowerCrafterItem.openSettingsInventory(event.player)
            event.isCancelled = true
        }
    }
) {

    private fun openSettingsInventory(player: Player) {
        val inventory =
            Inventory(InventoryType.CHEST_6_ROW, "<gradient:#672f0a:#944300><bold>FlowerCrafter".formatMini())
        inventory.styleRadialBackground(Material.BROWN_STAINED_GLASS_PANE, 6)
        player.openInventory(inventory)
    }

}