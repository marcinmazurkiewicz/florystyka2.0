import store from "@/store/store";

const hasRight = function (permission) {
    return store.getters["user/getLoggedUser"].authorities.includes(permission)
}

const hasAnyRight = function (permissions) {
    let hasPermission = false;
    permissions.forEach(permission => {
        if (hasRight(permission)) {
            hasPermission = true;
        }
    });
    return hasPermission;
}

const isLoggedUser = function () {
    return store.getters["user/isLoggedUser"]
}
export {hasRight, hasAnyRight, isLoggedUser};