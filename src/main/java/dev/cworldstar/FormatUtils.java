package dev.cworldstar;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.md_5.bungee.api.ChatColor;

public class FormatUtils {
	
	private static final MiniMessage MINI_MESSAGE_FORMATTER = MiniMessage.builder()
			.tags(TagResolver.standard())
			.build();
	
	public static String makeMachineCompletion(int work, int requiredWork) {
		double workCompletedPercent = ((double) work) / requiredWork;
		if(workCompletedPercent > 1) {
			return "<green>||||||||||||";
		}
		String processItemName = "||||||||||||";
		int substr = (int) Math.round(processItemName.length() * workCompletedPercent);
		String completed = "<green>" + processItemName.substring(0, substr) + "<red>";
		completed.replaceAll("|", "I");
		for(int x=substr; x<= processItemName.length(); x++) {
			completed = completed + "|";
		}
		return completed;
	}
	
	public FormatUtils() {
		throw new UnsupportedOperationException("This is a static class!");
	}
	
	public static Component createMiniMessageComponent(String text) {
		return MINI_MESSAGE_FORMATTER.deserialize("<!italic>" + text);
	}
	
	public static Component mm(String text) {
		return createMiniMessageComponent(text);
	}
	
	public static TextComponent createComponent(String text) {
		return Component.text(ChatColor.translateAlternateColorCodes('&', text));
	}
	
	public static List<Component> loreComponent(List<String> lore) {
		return lore.stream().map(str -> mm(str)).collect(Collectors.toList());
	}
	
	public static String formatString(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	public static Component formatAndCast(String s) {
		return Component.text(ChatColor.translateAlternateColorCodes('&', s));
	}
	
	public static List<TextComponent> getLore(ItemStack i) {
		ItemMeta meta = i.getItemMeta();
		List<TextComponent> lore = meta.lore().stream().map(component->(TextComponent) component).collect(Collectors.toList());
		return lore;
	}
	
	public static TextComponent replace(TextComponent toReplace, String pattern, String newValue) {
		return (TextComponent) toReplace.replaceText(TextReplacementConfig.builder().match(pattern).replacement(newValue).build());
	}
	
	public static List<TextComponent> replaceAll(List<TextComponent> toReplace, String pattern, String newValue) {
		toReplace.replaceAll(textComponent -> {
			 return replace(textComponent, pattern, newValue);
		});
		return toReplace;
	}
	
	public static TextComponent asText(String s) {
		return Component.text(ChatColor.translateAlternateColorCodes('&', s));
	}
	
	public static TextComponent format(String s) {
		return asText(s);
	}

	public static @NotNull List<Component> lore(String[] lore) {
		return loreComponent(Arrays.asList(lore));
	}
}
