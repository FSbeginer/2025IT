using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _2025부산_1과제.Template
{
    public partial class BF : Form
    {
        public BF()
        {
            InitializeComponent();
        }

        private void BF_Load(object sender, EventArgs e)
        {
            using (var bit = new Bitmap(Properties.Resources.logo))
            {
                Icon = Icon.FromHandle(bit.GetHicon());
            }
        }
        public static void msgInfo(string msg)
        {
            System.Windows.Forms.MessageBox.Show(msg, "정보", System.Windows.Forms.MessageBoxButtons.OK, System.Windows.Forms.MessageBoxIcon.Information);
        }
        public static void msgErr(string msg)
        {
            System.Windows.Forms.MessageBox.Show(msg, "정보", System.Windows.Forms.MessageBoxButtons.OK, System.Windows.Forms.MessageBoxIcon.Error);
        }
        public static Image GetImage(string path)
        {
            return Image.FromFile("./datafiles/" + path);
        }
        public void showPage(Form next)
        {
            Hide();
            next.ShowDialog();
            next.Dispose();
            Show();
        }
        public void showPage(string text)
        {
            var stack = new Stack<BF>(Application.OpenForms.Cast<BF>());
            while (stack.Count > 0)
            {
                var form = stack.Pop();
                if (form.Text == text)
                    break;
                else
                    form.Close();
            }
        }
        protected override void OnShown(EventArgs e)
        {
            base.OnShown(e);
            ActiveControl = null;
        }
        public virtual void UpdateForm()
        {

        }
    }
}
