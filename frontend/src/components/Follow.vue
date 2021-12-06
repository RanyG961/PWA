<template>
	<div>
		<button v-if="!utilisateur.follows" @click="followUser()">
			Follow
		</button>
		<h2 v-else>Abonné <button @click="unfollowUser()">UnFollow</button></h2>
	</div>
</template>

<script>
export default {
	name: "Follow",
	components: {},
	props: ["utilisateur"],
	methods: {
		followUser: function () {
			this.$store.dispatch("followUser", {
				username: this.$route.params.username,
			});
		},
		unfollowUser: function () {
			this.$store.dispatch("unfollowUser", {
				username: this.$route.params.username,
			});
		},
	},
};
</script>

<style>
</style>
