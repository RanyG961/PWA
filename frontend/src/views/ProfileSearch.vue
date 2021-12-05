<template>
	<profil> {{ $route.params.id }} </profil>
</template>

<script>
// import profil from "@/components/Profile.vue";

export default {
	name: "ProfileSearch",
	props: ["name"],
	// components: {
	// eslint-disable-next-line vue/no-unused-components
	// tweet,
	// profil,
	// },
};
</script>
