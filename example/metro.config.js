/**
 * Metro configuration for React Native
 * https://github.com/facebook/react-native
 *
 * @format
 */

const path = require("path");
const fs = require("fs");
const blacklist = require("metro-config/src/defaults/blacklist");

const packageJSON = JSON.parse(
  fs.readFileSync(path.resolve(__dirname + "/package.json"), {
    encoding: "utf-8"
  })
);

const extraNodeModules = Object.keys(packageJSON.dependencies).reduce(
  (r, name) => {
    const parts = packageJSON.dependencies[name].split("file:");
    if (1 < parts.length) {
      r[name] = path.resolve(__dirname + "/" + parts[1]);
    }
    return r;
  },
  {}
);

const watchFolders = Object.values(extraNodeModules);

const blacklistRE = blacklist([
  // /[.\/\\]+react\-native\-agora[\/\\]node_modules[\/\\]react\-native[\/\\].*/,
  // /[.\/\\]+react\-native\-agora[\/\\]samples[\/\\].*/,
  /[.\/\\]+react\-native\-agora(_git|\.git){0,1}[\/\\]node_modules[\/\\]react\-native[\/\\].*/,
  /[.\/\\]+react\-native\-agora(_git|\.git){0,1}[\/\\]samples[\/\\].*/
]);

module.exports = {
  transformer: {
    getTransformOptions: async () => ({
      transform: {
        experimentalImportSupport: false,
        inlineRequires: false
      }
    })
  },
  resolver: {
    extraNodeModules,
    blacklistRE
  },
  watchFolders
};
