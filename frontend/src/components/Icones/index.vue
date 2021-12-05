<template>
	<component :is="iconComponent" />
</template>

<script>
export default {
	name: "Icones",
	props: {
		icon: {
			type: String,
			default: "home",
		},
	},
	computed: {
		iconComponent() {
			return require(`./icons/${this.icon}`).default;
		},
	},
};
</script>

<style>
</style>
