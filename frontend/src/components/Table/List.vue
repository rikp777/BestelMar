<template>
  <div class="card">
    <div class="card-body">
      <div class="row mb-2">
        <div class="col">
          <router-link
            class="btn btn-primary"
            :to="{ name: 'home' }"
          >Back</router-link>
          <router-link
            class="btn btn-secondary"
            :to="{ name: 'tableCreate'}"
          >
            <b>+</b> Add new table
          </router-link>
        </div>
      </div>

      <div class="table-responsive">
        <table class="table table-striped table-condensed">
        <thead>
          <tr>
            <th>name</th>
            <th>Description</th>
            <th>Disabled</th>
            <th class="text-center">Edit/Disable</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="table in tables">
            <td>{{table.name}}</td>
            <td>{{table.description}}l</td>
            <td>
              <span v-bind:class="[table.disabled ? 'badge-danger' : 'badge-success']" class="badge badge-pill">{{table.disabled}}</span>
            </td>
            <td class="text-center">
              <router-link
                class="btn btn-warning btn-xs"
                :to="{ name: 'tableUpdate', params: { id: table.id }}"
              >
                Update
              </router-link>
              <router-link
                class="btn btn-danger btn-xs"
                :to="{ name: 'articleDelete', params: { id: table.id }}"
              >
                Delete
              </router-link>
            </td>
          </tr>
        </tbody>
      </table>
      </div>
    </div>
  </div>
</template>
<script>
    export default {
        name: "tableList",
        data(){
            return {
                article: {}
            }
        },
        computed: {
            tables(){
                return this.$store.getters.tables
            }
        },
        mounted(){
            this.getAllTables();
        },
        methods: {
            getAllTables(){
                this.$store.dispatch("getAllTables");
            },
        }
    }
</script>
