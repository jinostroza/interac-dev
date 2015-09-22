/**
 * Created by claudio on 21-09-15.
 */
var gulp = require("gulp");
var uglify = require("gulp-uglify");
var rename = require("gulp-rename");
var css = require("gulp-minify-css");

var getBaseDir = function(file) {
    return file.base;
};

gulp.task("js", function() {
    gulp.src(["public/**/*.js", "!public/**/*.min.js"])
        .pipe(uglify())
        .pipe(rename({
            extname: ".min.js"
        }))
        .pipe(gulp.dest(getBaseDir));
});

gulp.task("css", function() {
    gulp.src(["public/**/*.css", "!public/**/*.min.css"])
        .pipe(css())
        .pipe(rename({
            extname: ".min.css"
        }))
        .pipe(gulp.dest(getBaseDir));
});

gulp.task("watch", function () {
    gulp.watch(["public/**/*.js", "!public/**/*.min.js"], ["js"]);
    gulp.watch(["public/**/*.css", "!public/**/*.min.css"], ["css"]);
});

gulp.task("default", ["js", "css", "watch"]);