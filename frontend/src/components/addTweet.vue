<template>
		<div class="tweet_container">
			<textarea
				v-model="tweetContent"
				name="tweetContent"
				id="tweetContent"
				placeholder="Quoi de neuf le reuf ?"
			></textarea><br>
			<!-- <input type="file" id="photo" name="photo" accept="image/*" /> -->
			<input v-model="photo" type="text" name="photo" class="tweet_input" id="photo" /><br>
			<button @click="createTweet()" class="tweet_submit" type="submit">
				Envoyer tweet !
			</button>
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

.tweet_caontainer{
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}

.tweet_submit{
	text-align: center;
	width: 10em;
	height: 40px;
	padding: 12px 20px;
	box-sizing: border-box;
	border: 1px solid rgb(19, 24, 66);
	border-radius: 4px;
	background-color: #2196f3;
	font-size: 16px;
}

textarea {
  width: 20em;
  height: 100px;
  padding: 12px 20px;
  box-sizing: border-box;
  border: 2px solid rgb(19, 24, 66);
  border-radius: 4px;
  background-color: #edfdfd;
  font-size: 16px;
}

</style>
