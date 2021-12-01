<template>
	<div class="card">
		<h1 class="card_title" v-if="mode == 'login'">Connexion</h1>
		<h1 class="card_title" v-else>Inscription</h1>

		<p class="card_subtitle" v-if="mode == 'login'">
			Tu n'as pas encore de compte ?
			<span class="card_action" @click="switchToCreateAccount()">
				Créer un compte</span
			>
		</p>

		<p class="card_subtitle" v-else>
			Tu as déjà un compte
			<span class="card_action" @click="switchToLogin()">
				Se connecter</span
			>
		</p>
		<div class="form-row" v-if="mode != 'login'">
			<input
				v-model="lastName"
				class="form-row_input"
				type="text"
				name="lastName"
				id="lastName"
				placeholder="Nom"
				required
			/>
			<input
				v-model="firstName"
				class="form-row_input"
				type="text"
				name="firstName"
				id="firstName"
				placeholder="Prénom"
				required
			/>
		</div>
		<div class="form-row">
			<input
				v-model="email"
				v-if="mode != 'login'"
				class="form-row_input"
				type="email"
				name="email"
				id="email"
				placeholder="Adresse email"
				required
			/>
			<input
				v-model="username"
				class="form-row_input"
				type="text"
				name="username"
				id="username"
				placeholder="Nom d'utilisateur"
				required
			/>
		</div>
		<div class="form-row">
			<input
				v-model="password"
				class="form-row_input"
				type="password"
				name="password"
				id="password"
				placeholder="Mot de passe"
				required
			/>
			<input
				v-if="mode != 'login'"
				v-model="passwordConfirm"
				class="form-row_input"
				type="password"
				name="passwordConfirm"
				id="passwordConfirm"
			/>
		</div>
		<div class="form-row" v-if="mode != 'login'">
			<input
				v-model="birthDate"
				class="form-row_input"
				type="date"
				name="date"
				id="date"
			/>
		</div>
		<div class="form-row">
			<button
				class="button"
				:class="{ 'button--disabled': !validatedFields }"
				v-if="mode == 'login'"
			>
				Connexion
			</button>
			<button
				@click="createAccount()"
				class="button"
				:class="{ 'button--disabled': !validatedFields }"
				v-else
			>
				Créer un compte !
			</button>
		</div>
	</div>
</template>

<script>
export default {
	name: "Login",
	data: function () {
		return {
			mode: "login",
			lastName: "",
			firstName: "",
			email: "",
			username: "",
			password: "",
			passwordConfirm: "",
			birthDate: "",
		};
	},
	computed: {
		validatedFields: function () {
			if (this.mode != "login") {
				if (
					this.lastName != "" &&
					this.firstName != "" &&
					this.email != "" &&
					this.username != "" &&
					this.password != "" &&
					this.passwordConfirm != "" &&
					this.birthDate != ""
				) {
					if (this.password === this.passwordConfirm) {
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				if (this.email != "" && this.password != "") {
					return true;
				} else {
					return false;
				}
			}
		},
	},
	methods: {
		switchToCreateAccount: function () {
			this.mode = "create";
		},
		switchToLogin: function () {
			this.mode = "login";
		},
		createAccount: function () {
			this.$store.dispatch("createAccount", {
				lastName: this.lastName,
				firstName: this.firstName,
				email: this.email,
				username: this.username,
				password: this.password,
				// passwordConfirm: this.
				birthDate: this.birthDate,
			});
		},
	},
};
</script>

<style>
.form-row {
	display: flex;
	margin: 16px 0px;
	gap: 16px;
	flex-wrap: wrap;
}

.form-row_input {
	padding: 8px;
	border: none;
	border-radius: 8px;
	background: #f2f2f2;
	font-weight: 500;
	font-size: 16px;
	flex: 1;
	min-width: 100px;
	color: black;
}

.form-row_input::placeholder {
	color: #aaaaaa;
}
</style>
