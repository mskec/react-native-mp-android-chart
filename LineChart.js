import {
  PropTypes,
  requireNativeComponent,
  View
} from 'react-native';

const iface = {
  name: 'LineChart',
  propTypes: {
    ...View.propTypes,

    data: PropTypes.shape({
      datasets: PropTypes.arrayOf(PropTypes.shape({
        yValues: PropTypes.arrayOf(PropTypes.number),
        label: PropTypes.string,
        config: PropTypes.shape({
          color: PropTypes.string,
          colors: PropTypes.arrayOf(PropTypes.string),

          highlightColor: PropTypes.string,

          drawHighlightIndicators: PropTypes.bool,
          drawVerticalHighlightIndicator: PropTypes.bool,
          drawHorizontalHighlightIndicator: PropTypes.bool,
          highlightLineWidth: PropTypes.number,

          fillColor: PropTypes.string,
          fillAlpha: PropTypes.number,
          drawFilled: PropTypes.bool,
          lineWidth: (props, propName, componentName) => {
            let lineWidth = props[propName];
            if (typeof lineWidth !== 'Number' && lineWidth < 0.2 || lineWidth > 10) {
              return new Error(
                `Invalid prop ${propName} supplied to '${componentName}'. Value must be number and between 0.2f and 10f`
              );
            }
          },

          circleRadius: PropTypes.number,
          drawCircles: PropTypes.bool,
          drawCubic: PropTypes.bool,
          drawCubicIntensity: PropTypes.number,
          circleColor: PropTypes.string,
          circleColors: PropTypes.arrayOf(PropTypes.string),
          circleColorHole: PropTypes.string,
          drawCircleHole: PropTypes.bool,

          dashedLine: PropTypes.shape({
            lineLength: PropTypes.number.isRequired,
            spaceLength: PropTypes.number.isRequired,
            phase: PropTypes.number,
          })
        })
      })),
      xValues: PropTypes.arrayOf(PropTypes.string)
    }),

    legend: PropTypes.shape({
      enabled: PropTypes.bool,

      textColor: PropTypes.string,
      textSize: PropTypes.number,
      fontFamily: PropTypes.string,
      fontStyle: PropTypes.number,

      wordWrapEnabled: PropTypes.bool,
      maxSizePercent: PropTypes.number,

      position: PropTypes.string,
      form: PropTypes.string,
      formSize: PropTypes.number,
      xEntrySpace: PropTypes.number,
      yEntrySpace: PropTypes.number,
      formToTextSpace: PropTypes.number,

      custom: PropTypes.shape({
        colors: PropTypes.arrayOf(PropTypes.string),
        labels: PropTypes.arrayOf(PropTypes.string)
      })
    })
  }
};

export default requireNativeComponent('MPAndroidLineChart', iface);
