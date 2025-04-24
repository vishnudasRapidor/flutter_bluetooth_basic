// This file is used to include the Flutter plugin into the example app build

// Get reference to the calling gradle project
def gradle = getBinding().getVariable('gradle')
def root = gradle.rootProject

// Get the root project directory
def flutterProjectRoot = root.projectDir.parentFile.parentFile

// Include the Flutter plugin
gradle.include ':flutter_bluetooth_basic'
gradle.project(':flutter_bluetooth_basic').projectDir = new File(flutterProjectRoot, 'android')

// Load the plugins
def plugins = new Properties()
def pluginsFile = new File(flutterProjectRoot, '.flutter-plugins')
if (pluginsFile.exists()) {
    pluginsFile.withReader('UTF-8') { reader -> plugins.load(reader) }
}

plugins.each { name, path ->
    def pluginDirectory = new File(flutterProjectRoot.absolutePath, path)
    def androidPluginDir = new File(pluginDirectory, "android")
    if (androidPluginDir.exists()) {
        gradle.include ":$name"
        gradle.project(":$name").projectDir = androidPluginDir
    }
}
