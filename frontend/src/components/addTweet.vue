<template>
	<div>
		<div class="">
			<textarea
				v-model="tweetContent"
				name="tweetContent"
				id="tweetContent"
				placeholder="Quoi de neuf le reuf ?"
			></textarea>
			<!-- <input type="file" id="photo" name="photo" accept="image/*" /> -->
			<input v-model="photo" type="text" name="photo" id="photo" />
			<button @click="createTweet()" type="submit">
				Envoyer tweet !
			</button>
		</div>
	</div>
</template>

<script>
export default {
	name: "addTweet",
	components: {},
	data: () => {
		return {
			tweetContent: "",
			photo: "null",
		};
	},
	props: [],
	computed: {
		validatedFields: function () {
			if (this.tweetContent != "") return true;
			return false;
		},
	},
	methods: {
		createTweet: function () {
			this.$store.dispatch("createTweet", {
				content: this.tweetContent,
				media: this.photo,
			});
		},
	},
};
</script>

<style>
</style>
