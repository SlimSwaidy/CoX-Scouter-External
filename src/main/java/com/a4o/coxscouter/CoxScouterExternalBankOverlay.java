package com.a4o.coxscouter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.Item;
import net.runelite.api.ItemContainer;
import net.runelite.api.gameval.InventoryID;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.game.ItemVariationMapping;
import net.runelite.client.ui.overlay.WidgetItemOverlay;

public class CoxScouterExternalBankOverlay extends WidgetItemOverlay
{
	private final Client client;
	private final CoxScouterExternalPlugin plugin;
	private final CoxScouterExternalConfig config;
	private int lastGameCycle = -1;
	private Set<Integer> cachedPlayerItems;

	@Inject
	private CoxScouterExternalBankOverlay(Client client, CoxScouterExternalPlugin plugin, CoxScouterExternalConfig config)
	{
		this.client = client;
		this.plugin = plugin;
		this.config = config;
		showOnBank();
	}

	@Override
	public void renderItemOverlay(Graphics2D graphics, int itemId, WidgetItem widgetItem) {
		if (!config.highlightBankItems()) {
			return;
		}

		if (!isRecommendedItem(itemId)) {
			return;
		}

		if (playerHasItem(itemId)) {
			return;
}

		highlightItem(graphics, widgetItem);
	}

	private boolean isRecommendedItem(int itemId)
	{
		Set<Integer> recommendedIds = plugin.getRaidRecommendedItems();
		if (recommendedIds.contains(itemId))
		{
			return true;
		}

		int baseId = ItemVariationMapping.map(itemId);
		for (int recommendedId : recommendedIds)
		{
			if (ItemVariationMapping.map(recommendedId) == baseId)
			{
				return true;
			}
		}

		return false;
	}

	private boolean playerHasItem(int itemId)
	{
		Set<Integer> playerItems = getPlayerItems();
		if (playerItems.contains(itemId))
		{
			return true;
		}

		int baseItemId = ItemVariationMapping.map(itemId);
		for (int playerItemId : playerItems)
		{
			if (playerItemId != -1 && ItemVariationMapping.map(playerItemId) == baseItemId)
			{
				return true;
			}
		}
		return false;
	}

	private Set<Integer> getPlayerItems()
	{
		int currentCycle = client.getGameCycle();
		if (currentCycle == lastGameCycle && cachedPlayerItems != null)
		{
			// to avoid building a new set of player items for each banked item
			return cachedPlayerItems;
		}
		lastGameCycle = currentCycle;
		cachedPlayerItems = getPlayerItemIds();
		return cachedPlayerItems;
	}

	private Set<Integer> getPlayerItemIds() {
		Set<Integer> playerItemIds = new HashSet<>();
		ItemContainer inventory = client.getItemContainer(InventoryID.INV);
		if (inventory != null)
		{
			for (Item item : inventory.getItems())
			{
				playerItemIds.add(item.getId());
			}
		}

		ItemContainer equipment = client.getItemContainer(InventoryID.WORN);
		if (equipment != null)
		{
			for (Item item : equipment.getItems())
			{
				playerItemIds.add(item.getId());
			}
		}

		return playerItemIds;
	}

	private void highlightItem(Graphics2D graphics, WidgetItem widgetItem) {
		Rectangle bounds = widgetItem.getCanvasBounds();
		if (bounds == null)
		{
			return;
		}

		Color color = config.highlightBankItemsColor();
		graphics.setColor(color);
		graphics.fill(bounds);
	}
}